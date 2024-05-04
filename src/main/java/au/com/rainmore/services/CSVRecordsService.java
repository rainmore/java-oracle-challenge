package au.com.rainmore.services;

import au.com.rainmore.domains.CSVRecord;
import au.com.rainmore.domains.GeoZone;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CSVRecordsService {

    /**
     * @param csvRecords
     * @return The number of unique customerId for each contractId
     */
    public Map<Long, Integer> countCustomerIdByContractId(List<CSVRecord> csvRecords) {
        return csvRecords.stream().collect(Collectors.groupingBy(
                CSVRecord::getContractId,
                Collectors.collectingAndThen(
                        Collectors.mapping(CSVRecord::getCustomerId, Collectors.toSet()),
                        Set::size)));
    }

    /**
     * @param csvRecords
     * @return The list of unique customerId for each GeoZone
     */
    public Map<GeoZone, Set<Long>> groupCustomerIdByGeoZone(List<CSVRecord> csvRecords) {
        return csvRecords.stream().collect(Collectors.groupingBy(
                CSVRecord::getGeoZone,
                Collectors.mapping(CSVRecord::getCustomerId, Collectors.toSet())));
    }

    /**
     * @param csvRecords
     * @return The number of unique customerId for each GeoZone
     */
    public Map<GeoZone, Integer> countCustomerIdByGeoZone(List<CSVRecord> csvRecords) {
        return csvRecords.stream().collect(Collectors.groupingBy(
                CSVRecord::getGeoZone,
                Collectors.collectingAndThen(
                        Collectors.mapping(CSVRecord::getCustomerId, Collectors.toSet()),
                        Set::size)));
    }

    /**
     * @param csvRecords
     * @return The average buildduration for each GeoZone
     */
    public Map<GeoZone, Duration> calculateAveDurationByGeoZone(List<CSVRecord> csvRecords) {
        return csvRecords.stream().collect(Collectors.groupingBy(
                CSVRecord::getGeoZone,
                Collectors.collectingAndThen(
                        Collectors.mapping(CSVRecord::getBuildDuration, Collectors.toSet()),
                        set -> {
                            if (set.isEmpty()) {
                                return Duration.ZERO;
                            }
                            else {
                                long sum = set.stream().mapToLong(Duration::toNanos).sum();
                                return Duration.ofNanos(sum / set.size());
                            }
                        })
                ));
    }

}
