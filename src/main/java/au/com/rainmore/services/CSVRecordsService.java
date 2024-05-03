package au.com.rainmore.services;

import au.com.rainmore.domains.CSVRecord;
import au.com.rainmore.domains.GeoZone;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CSVRecordsService {

    /**
     * @param csvRecords
     * @return The number of unique customerId for each contractId
     */
    public Map<Long, Integer> countCustomerIdByContractId(List<CSVRecord> csvRecords) {
        Map<Long, Integer> result = new HashMap<>();
        for (Map.Entry<Long, Set<Long>> entry:  groupCustomerIdByContractId(csvRecords).entrySet()) {
            result.put(entry.getKey(), entry.getValue().size());
        }
        return result;
    }

    private Map<Long, Set<Long>> groupCustomerIdByContractId(List<CSVRecord> csvRecords) {
        Map<Long, Set<Long>> result = new HashMap<>();

        return result;
    }

    /**
     * @param csvRecords
     * @return The list of unique customerId for each GeoZone
     */
    public Map<GeoZone, Set<Long>> groupCustomerIdByGeoZone(List<CSVRecord> csvRecords) {
        Map<GeoZone, Set<Long>> result = new HashMap<>();
        return result;
    }

    /**
     * @param csvRecords
     * @return The number of unique customerId for each GeoZone
     */
    public Map<GeoZone, Integer> countCustomerIdByGeoZone(List<CSVRecord> csvRecords) {
        Map<GeoZone, Integer> result = new HashMap<>();
        for (Map.Entry<GeoZone, Set<Long>> entry:  groupCustomerIdByGeoZone(csvRecords).entrySet()) {
            result.put(entry.getKey(), entry.getValue().size());
        }
        return result;
    }

    private Map<GeoZone, Set<Duration>> groupDurationByGeoZone(List<CSVRecord> csvRecords) {
        Map<GeoZone, Set<Duration>> result = new HashMap<>();

        return result;
    }

    /**
     * @param csvRecords
     * @return The average buildduration for each GeoZone
     */
    public Map<GeoZone, Duration> calculateAveDurationByGeoZone(List<CSVRecord> csvRecords) {
        Map<GeoZone, Duration> result = new HashMap<>();
        for (Map.Entry<GeoZone, Set<Duration>> entry:  groupDurationByGeoZone(csvRecords).entrySet()) {
            // TODO
        }
        return result;
    }

}
