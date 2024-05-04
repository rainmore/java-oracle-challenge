package au.com.rainmore.services.reports;

import au.com.rainmore.domains.CSVRecord;
import au.com.rainmore.domains.GeoZone;
import au.com.rainmore.services.CSVRecordsService;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ReportService {

    private final CSVRecordsService csvRecordsService;

    public ReportService(CSVRecordsService csvRecordsService) {
        this.csvRecordsService = csvRecordsService;
    }

    public String buildReport(List<CSVRecord> csvRecords) {
        StringBuilder sb = new StringBuilder();

        buildCountCustomerIdByContractIdReport(csvRecords, sb);
        // add empty line
        build(sb, "");

        buildCountCustomerIdByGeoZoneReport(csvRecords, sb);
        // add empty line
        build(sb, "");

        buildCalculateAveDurationByGeoZoneReport(csvRecords, sb);
        // add empty line
        build(sb, "");

        buildGroupCustomerIdByGeoZoneReport(csvRecords, sb);
        return sb.toString().trim();
    }

    private void buildCountCustomerIdByContractIdReport(List<CSVRecord> csvRecords, StringBuilder sb) {
        Map<Long, Integer> data = csvRecordsService.countCustomerIdByContractId(csvRecords);

        String tpl = "The number of unique customerId for contractId %d is %d";
        data.keySet().stream().sorted()
                .forEach(key -> build(sb, String.format(tpl, key, data.get(key))));
    }

    private void buildCountCustomerIdByGeoZoneReport(List<CSVRecord> csvRecords, StringBuilder sb) {
        Map<GeoZone, Integer> data = csvRecordsService.countCustomerIdByGeoZone(csvRecords);

        String tpl = "The number of unique customerId for geozone \"%s\" is %d";
        data.keySet().stream().sorted()
                .forEach(key -> build(sb, String.format(tpl, key, data.get(key))));
    }

    private void buildCalculateAveDurationByGeoZoneReport(List<CSVRecord> csvRecords, StringBuilder sb) {
        Map<GeoZone, Duration> data = csvRecordsService.calculateAveDurationByGeoZone(csvRecords);

        String tpl = "The average buildduration for geozone \"%s\" is %ds";
        data.keySet().stream().sorted()
                .forEach(key -> build(sb, String.format(tpl, key, data.get(key).getSeconds())));
    }

    private void buildGroupCustomerIdByGeoZoneReport(List<CSVRecord> csvRecords, StringBuilder sb) {
        Map<GeoZone, Set<Long>> data = csvRecordsService.groupCustomerIdByGeoZone(csvRecords);

        String tpl = "The list of unique customerId for geozone \"%s\" is (%s)";
        data.keySet().stream().sorted()
                .forEach(key -> {
                    String values = data.get(key).stream().sorted().map(String::valueOf).collect(Collectors.joining(", "));
                    build(sb, String.format(tpl, key, values));
                });
    }

    private static void build(StringBuilder sb, String str) {
        sb.append(str)
                .append(System.lineSeparator());
    }

}
