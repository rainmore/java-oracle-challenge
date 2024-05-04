package au.com.rainmore.services.csv;

import au.com.rainmore.domains.CSVRecord;
import au.com.rainmore.utils.ResourceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.text.ParseException;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CSVRecordParsingServiceTest {

    private CSVRecordParsingService service;

    @BeforeEach
    void setUp() {
        service = new CSVRecordParsingService(new CSVRecordLineParsingService());
    }

    @Test
    void test_parseCSV_withValidInputStream() throws Exception {
        Supplier<InputStream> inputStream = ResourceUtils.loadResource("test-csv-valid.csv");
        assertDoesNotThrow(() -> service.parseCSV(inputStream.get()));
        List<CSVRecord> CSVRecords = service.parseCSV(inputStream.get());
        assertEquals(5, CSVRecords.size());
    }

    @Test
    void test_parseCSV_withEmptyInput() throws Exception {
        Supplier<InputStream> inputStream = ResourceUtils.loadResource("test-csv-empty.csv");
        assertDoesNotThrow(() -> service.parseCSV(inputStream.get()));
        List<CSVRecord> CSVRecords = service.parseCSV(inputStream.get());
        assertEquals(0, CSVRecords.size());
    }

    @Test
    void test_parseCSV_withInvalidInput() throws Exception {
        Supplier<InputStream> inputStream = ResourceUtils.loadResource("test-csv-invalid.csv");
        assertThrows(ParseException.class, () -> service.parseCSV(inputStream.get()));
    }

    @Test
    void test_parseCSV_withValidString() throws Exception {
        String string = """
2343225,2345,us_east,RedTeam,ProjectApple,3445s
1223456,2345,us_west,BlueTeam,ProjectBanana,2211s
3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s
1233456,2345,us_west,BlueTeam,ProjectDate,2221s
3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s
                """.trim();
        assertDoesNotThrow(() -> service.parseCSV(string));
        List<CSVRecord> CSVRecords = service.parseCSV(string);
        assertEquals(5, CSVRecords.size());
    }

    @Test
    void test_parseCSV_withEmptyString() throws Exception {
        String string = "";
        assertThrows(ParseException.class, () -> service.parseCSV(string));
    }

    @Test
    void test_parseCSV_withInvalidString() throws Exception {
        String string = """
2343225,2345,us_east,RedTeam,ProjectApple,3445s
1223456,2345,us_west,BlueTeam,ProjectBanana,2211
3244332,2346,eu_west,YellowTeam3,,4322s
1233456,,us_west,BlueTeam,ProjectDate,2221s
3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s
                """.trim();
        assertThrows(ParseException.class, () -> service.parseCSV(string));
    }

}
