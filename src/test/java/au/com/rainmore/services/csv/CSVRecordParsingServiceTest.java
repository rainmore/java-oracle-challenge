package au.com.rainmore.services.csv;

import au.com.rainmore.domains.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVRecordParsingServiceTest {

    private CSVRecordParsingService service;

    @BeforeEach
    void setUp() {
        service = new CSVRecordParsingService(new CSVRecordLineParsingService());
    }

    @Test
    void test_parseCSV_withValidArgumentArray() throws Exception {
       String[] args = new String[]{getValidCSVString()};

        assertDoesNotThrow(() -> service.parseCSV(args));
        List<CSVRecord> CSVRecords = service.parseCSV(args);
        assertEquals(5, CSVRecords.size());
    }

    @Test
    void test_parseCSV_withEmptyArgumentArray() throws Exception {
        String[] args = new String[]{};
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.parseCSV(args));
        assertEquals("Please provide CSV string", exception.getMessage());
    }

    @Test
    void test_parseCSV_withInvalidArgumentArray() throws Exception {
        String[] args = new String[]{"   "};
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.parseCSV(args));
        assertEquals("Please provide CSV string", exception.getMessage());
    }

    @Test
    void test_parseCSV_withValidString() throws Exception {
        String string = getValidCSVString();
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

    private String getValidCSVString() {
        return """
2343225,2345,us_east,RedTeam,ProjectApple,3445s
1223456,2345,us_west,BlueTeam,ProjectBanana,2211s
3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s
1233456,2345,us_west,BlueTeam,ProjectDate,2221s
3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s
                """.trim();
    }

}
