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

    @Test
    void test_fromCSVLine_withInvalidCSVLine() throws Exception {
        String emptyCSVLine = "";

        ParseException emptyException = assertThrows(ParseException.class, () -> service.fromCSVLine(emptyCSVLine));
        assertEquals(0, emptyException.getErrorOffset());
        assertEquals("CSV Line cannot be empty", emptyException.getMessage());

        String invalidCSVLine1 = "customer,2346,eu_west,YellowTeam3,ProjectEgg,4122s,invalid";

        ParseException invalidException1 = assertThrows(ParseException.class, () -> service.fromCSVLine(invalidCSVLine1));
        assertEquals(0, invalidException1.getErrorOffset());
        assertEquals("Invalid CSV format", invalidException1.getMessage());

        String invalidCSVLine2 = "customer,2346,eu_west,YellowTeam3,ProjectEgg,";

        ParseException invalidException2 = assertThrows(ParseException.class, () -> service.fromCSVLine(invalidCSVLine2));
        assertEquals(0, invalidException2.getErrorOffset());
        assertEquals("Invalid CSV format", invalidException2.getMessage());
    }

    @Test
    void test_fromCSVLine_withEmptyCustomerId_or_InvalidCustomerId() throws Exception {
        String emptyCSVLine = ",2346,eu_west,YellowTeam3,ProjectEgg,4122s";

        ParseException emptyException = assertThrows(ParseException.class, () -> service.fromCSVLine(emptyCSVLine));
        assertEquals(0, emptyException.getErrorOffset());
        assertEquals("Customer Id is null or empty", emptyException.getMessage());

        String invalidCSVLine = "customer,2346,eu_west,YellowTeam3,ProjectEgg,4122s";

        ParseException invalidException = assertThrows(ParseException.class, () -> service.fromCSVLine(invalidCSVLine));
        assertEquals(0, invalidException.getErrorOffset());
        assertEquals("Customer Id \"customer\" is not a number", invalidException.getMessage());
    }

    @Test
    void test_fromCSVLine_withEmptyContractId_or_InvalidContractId() throws Exception {
        String emptyCSVLine = "3244132,,eu_west,YellowTeam3,ProjectEgg,4122s";

        ParseException emptyException = assertThrows(ParseException.class, () -> service.fromCSVLine(emptyCSVLine));
        assertEquals(1, emptyException.getErrorOffset());
        assertEquals("Contract Id is null or empty", emptyException.getMessage());

        String invalidCSVLine = "3244132,contract,eu_west,YellowTeam3,ProjectEgg,4122s";

        ParseException invalidException = assertThrows(ParseException.class, () -> service.fromCSVLine(invalidCSVLine));
        assertEquals(1, invalidException.getErrorOffset());
        assertEquals("Contract Id \"contract\" is not a number", invalidException.getMessage());
    }

    @Test
    void test_fromCSVLine_withEmptyGeoCode_or_InvalidGeoCode() throws Exception {
        String emptyCSVLine = "3244132,2346,,YellowTeam3,ProjectEgg,4122s";

        ParseException emptyException = assertThrows(ParseException.class, () -> service.fromCSVLine(emptyCSVLine));
        assertEquals(2, emptyException.getErrorOffset());
        assertEquals("Geo Zone is null or empty", emptyException.getMessage());

        String invalidCSVLine = "3244132,2346,eu_west_1,YellowTeam3,ProjectEgg,4122s";

        ParseException invalidException = assertThrows(ParseException.class, () -> service.fromCSVLine(invalidCSVLine));
        assertEquals(2, invalidException.getErrorOffset());
        assertEquals("Geo Zone \"eu_west_1\" is invalid", invalidException.getMessage());
    }

    @Test
    void test_fromCSVLine_withEmptyTeamCode() throws Exception {
        String emptyCSVLine = "1233456,2345,us_west,,ProjectDate,2221s";

        ParseException emptyException = assertThrows(ParseException.class, () -> service.fromCSVLine(emptyCSVLine));
        assertEquals(3, emptyException.getErrorOffset());
        assertEquals("Team Code is null or empty", emptyException.getMessage());
    }

    @Test
    void test_fromCSVLine_withEmptyProjectCode() throws Exception {
        String emptyCSVLine = "3244132,2346,eu_west,YellowTeam3,,4122s";

        ParseException emptyException = assertThrows(ParseException.class, () -> service.fromCSVLine(emptyCSVLine));
        assertEquals(4, emptyException.getErrorOffset());
        assertEquals("Project Code is null or empty", emptyException.getMessage());
    }

    @Test
    void test_fromCSVLine_withInvalidDuration() throws Exception {
        String invalidCSVLine = "3244132,2346,eu_west,YellowTeam3,ProjectEgg,2323";

        ParseException invalidException = assertThrows(ParseException.class, () -> service.fromCSVLine(invalidCSVLine));
        assertEquals(5, invalidException.getErrorOffset());
    }

}
