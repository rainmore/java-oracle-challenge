package au.com.rainmore.services;

import au.com.rainmore.domains.CSVRecord;
import au.com.rainmore.domains.GeoZone;
import au.com.rainmore.utils.features.CSVRecordFeatures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static au.com.rainmore.utils.AssertionUtils.assertSetEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CSVRecordsServiceTest {

    private CSVRecordsService reportService;

    private CSVRecordFeatures csvRecordFeatures;

    @BeforeEach
    void setUp() {
        reportService = new CSVRecordsService();
        csvRecordFeatures = new CSVRecordFeatures();
    }

    @Test
    void test_countCustomerIdByContractId_returns_expected() {
        List<CSVRecord> csvRecords = csvRecordFeatures.getValidCSVRecords();
        Map<Long, Integer> results = reportService.countCustomerIdByContractId(csvRecords);

        assertEquals(2, results.size());
        Set<Long> expectedKeys = Set.of(2345L, 2346L);
        assertSetEquals(Set.of(2345L, 2346L), results.keySet());
        assertEquals(3, results.get(2345L));
        assertEquals(2, results.get(2346L));
    }

    @Test
    void test_groupCustomerIdByGeoZone_returns_expected() {
        List<CSVRecord> csvRecords = csvRecordFeatures.getValidCSVRecords();
        Map<GeoZone, Set<Long>> results = reportService.groupCustomerIdByGeoZone(csvRecords);

        assertEquals(3, results.size());
        assertSetEquals(Set.of(GeoZone.US_EAST, GeoZone.US_WEST, GeoZone.EU_WEST), results.keySet());
        assertSetEquals(Set.of(2343225L), results.get(GeoZone.US_EAST));
        assertSetEquals(Set.of(1223456L, 1233456L), results.get(GeoZone.US_WEST));
        assertSetEquals(Set.of(3244332L, 3244132L), results.get(GeoZone.EU_WEST));
    }

    @Test
    void test_countCustomerIdByGeoZone_returns_expected() {
        List<CSVRecord> csvRecords = csvRecordFeatures.getValidCSVRecords();
        Map<GeoZone, Integer> results = reportService.countCustomerIdByGeoZone(csvRecords);

        assertEquals(3, results.size());
        assertSetEquals(Set.of(GeoZone.US_EAST, GeoZone.US_WEST, GeoZone.EU_WEST), results.keySet());
        assertEquals(1, results.get(GeoZone.US_EAST));
        assertEquals(2, results.get(GeoZone.US_WEST));
        assertEquals(2, results.get(GeoZone.EU_WEST));
    }

    @Test
    void test_calculateAveDurationByGeoZone_returns_expected() {
        List<CSVRecord> csvRecords = csvRecordFeatures.getValidCSVRecords();
        Map<GeoZone, Duration> results = reportService.calculateAveDurationByGeoZone(csvRecords);

        assertEquals(3, results.size());
        assertSetEquals(Set.of(GeoZone.US_EAST, GeoZone.US_WEST, GeoZone.EU_WEST), results.keySet());
        assertEquals(Duration.of(3445, ChronoUnit.SECONDS), results.get(GeoZone.US_EAST));
        assertEquals(Duration.of(2216, ChronoUnit.SECONDS), results.get(GeoZone.US_WEST));
        assertEquals(Duration.of(4222, ChronoUnit.SECONDS), results.get(GeoZone.EU_WEST));
    }

}
