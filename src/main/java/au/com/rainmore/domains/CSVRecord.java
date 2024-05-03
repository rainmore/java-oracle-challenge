package au.com.rainmore.domains;

import java.time.Duration;

public class CSVRecord {

    private Long customerId;

    private Long contractId;

    private GeoZone geoZone;

    private String teamCode;

    private String projectCode;

    private Duration buildDuration;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public GeoZone getGeoZone() {
        return geoZone;
    }

    public void setGeoZone(GeoZone geoZone) {
        this.geoZone = geoZone;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Duration getBuildDuration() {
        return buildDuration;
    }

    public void setBuildDuration(Duration buildDuration) {
        this.buildDuration = buildDuration;
    }
}
