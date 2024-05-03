package au.com.rainmore.services;

import au.com.rainmore.domains.CSVRecord;
import au.com.rainmore.domains.GeoZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class CSVRecordLineParsingServiceTest {

    private CSVRecordLineParsingService service;

    @BeforeEach
    void setUp() {
        service = new CSVRecordLineParsingService();
    }

    @Test
    void test_fromCSVLine_withValidCSVLine1() throws Exception {
        String csvLine = "2343225,2345,us_east,RedTeam,ProjectApple,3445s";

        assertDoesNotThrow(() -> service.fromCSVLine(csvLine));

        CSVRecord csvRecord = service.fromCSVLine(csvLine);
        assertEquals(2343225L, csvRecord.getCustomerId());
        assertEquals(2345L, csvRecord.getContractId());
        assertEquals(GeoZone.US_EAST, csvRecord.getGeoZone());
        assertEquals("RedTeam", csvRecord.getTeamCode());
        assertEquals("ProjectApple", csvRecord.getProjectCode());
        assertEquals(Duration.of(3445, ChronoUnit.SECONDS), csvRecord.getBuildDuration());
    }

    @Test
    void test_fromCSVLine_withValidCSVLine2() throws Exception {
        String csvLine = "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s";

        assertDoesNotThrow(() -> service.fromCSVLine(csvLine));

        CSVRecord csvRecord = service.fromCSVLine(csvLine);
        assertEquals(1223456L, csvRecord.getCustomerId());
        assertEquals(2345L, csvRecord.getContractId());
        assertEquals(GeoZone.US_WEST, csvRecord.getGeoZone());
        assertEquals("BlueTeam", csvRecord.getTeamCode());
        assertEquals("ProjectBanana", csvRecord.getProjectCode());
        assertEquals(Duration.of(2211, ChronoUnit.SECONDS), csvRecord.getBuildDuration());
    }

    @Test
    void test_fromCSVLine_withValidCSVLine3() throws Exception {
        String csvLine = "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s";

        assertDoesNotThrow(() -> service.fromCSVLine(csvLine));

        CSVRecord csvRecord = service.fromCSVLine(csvLine);
        assertEquals(3244332L, csvRecord.getCustomerId());
        assertEquals(2346L, csvRecord.getContractId());
        assertEquals(GeoZone.EU_WEST, csvRecord.getGeoZone());
        assertEquals("YellowTeam3", csvRecord.getTeamCode());
        assertEquals("ProjectCarrot", csvRecord.getProjectCode());
        assertEquals(Duration.of(4322, ChronoUnit.SECONDS), csvRecord.getBuildDuration());
    }

    @Test
    void test_fromCSVLine_withValidCSVLine4() throws Exception {
        String csvLine = "1233456,2345,us_west,BlueTeam,ProjectDate,2221s";

        assertDoesNotThrow(() -> service.fromCSVLine(csvLine));

        CSVRecord csvRecord = service.fromCSVLine(csvLine);
        assertEquals(1233456L, csvRecord.getCustomerId());
        assertEquals(2345L, csvRecord.getContractId());
        assertEquals(GeoZone.US_WEST, csvRecord.getGeoZone());
        assertEquals("BlueTeam", csvRecord.getTeamCode());
        assertEquals("ProjectDate", csvRecord.getProjectCode());
        assertEquals(Duration.of(2221, ChronoUnit.SECONDS), csvRecord.getBuildDuration());
    }

    @Test
    void test_fromCSVLine_withValidCSVLine5() throws Exception {
        String csvLine = "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";

        assertDoesNotThrow(() -> service.fromCSVLine(csvLine));

        CSVRecord csvRecord = service.fromCSVLine(csvLine);
        assertEquals(3244132L, csvRecord.getCustomerId());
        assertEquals(2346L, csvRecord.getContractId());
        assertEquals(GeoZone.EU_WEST, csvRecord.getGeoZone());
        assertEquals("YellowTeam3", csvRecord.getTeamCode());
        assertEquals("ProjectEgg", csvRecord.getProjectCode());
        assertEquals(Duration.of(4122, ChronoUnit.SECONDS), csvRecord.getBuildDuration());
    }

}
