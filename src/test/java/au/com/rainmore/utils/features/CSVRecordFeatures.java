package au.com.rainmore.utils.features;

import au.com.rainmore.domains.CSVRecord;
import au.com.rainmore.domains.GeoZone;

import java.time.Duration;
import java.util.List;

public class CSVRecordFeatures {

    public List<CSVRecord> getValidCSVRecords() {
        return List.of(
                buildCSVRecord(2343225L, 2345L, GeoZone.US_EAST, "RedTeam", "ProjectApple", Duration.ofSeconds(3445L)),
                buildCSVRecord(1223456L, 2345L, GeoZone.US_WEST, "BlueTeam", "ProjectBanana", Duration.ofSeconds(2211L)),
                buildCSVRecord(3244332L, 2346L, GeoZone.EU_WEST, "YellowTeam3", "ProjectCarrot", Duration.ofSeconds(4322L)),
                buildCSVRecord(1233456L, 2345L, GeoZone.US_WEST, "BlueTeam", "ProjectDate", Duration.ofSeconds(2221L)),
                buildCSVRecord(3244132L, 2346L, GeoZone.EU_WEST, "YellowTeam3", "ProjectEgg", Duration.ofSeconds(4122L))
                      );
    }


    private CSVRecord buildCSVRecord(Long customerId,
                                     Long contractId,
                                     GeoZone geoZone,
                                     String teamCode,
                                     String projectCode,
                                     Duration buildDuration) {
        CSVRecord csvRecord = new CSVRecord();
        csvRecord.setCustomerId(customerId);
        csvRecord.setContractId(contractId);
        csvRecord.setGeoZone(geoZone);
        csvRecord.setTeamCode(teamCode);
        csvRecord.setProjectCode(projectCode);
        csvRecord.setBuildDuration(buildDuration);
        return csvRecord;
    }

}
