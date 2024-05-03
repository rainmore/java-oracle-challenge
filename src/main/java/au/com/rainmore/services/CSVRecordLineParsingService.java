package au.com.rainmore.services;

import au.com.rainmore.domains.CSVRecord;
import au.com.rainmore.domains.GeoZone;

import java.text.ParseException;
import java.time.Duration;
import java.time.format.DateTimeParseException;

public class CSVRecordLineParsingService {

    public CSVRecord fromCSVLine(String s) throws ParseException {
        if (s == null || s.isEmpty()) {
            throw new ParseException("CSV Line cannot be empty", 0);
        }
        String[] parts = s.split(",");

        if (parts.length != 6) {
            throw new ParseException("Invalid CSV format", 0);
        }

        CSVRecord csvRecord = new CSVRecord();
        csvRecord.setCustomerId(parseNumber(parts, 0, "Customer Id"));
        csvRecord.setContractId(parseNumber(parts, 1, "Contract Id"));
        csvRecord.setGeoZone(parseGeoZone(parts, 2, "Geo Zone"));
        csvRecord.setTeamCode(sanitizeString(parts, 3, "Team Code"));
        csvRecord.setProjectCode(sanitizeString(parts, 4, "Project Code"));
        csvRecord.setBuildDuration(parseBuildDuration(parts, 5, "Build Duration"));

        return csvRecord;
    }

    private String sanitizeString(String[] parts, int offset, String propertyName) throws ParseException {
        if (parts[offset] == null || parts[offset].isEmpty()) {
            throw new ParseException(String.format("%s is null or empty", propertyName), offset);
        }
        return parts[offset].trim();
    }

    private Long parseNumber(String[] parts, int offset, String propertyName) throws ParseException {
        String value = sanitizeString(parts, offset, propertyName);

        try {
            return Long.parseLong(value);
        } catch (NumberFormatException ex) {
            throw new ParseException(String.format("%s \"%s\" is not a number", propertyName, value), offset);
        }
    }

    private Duration parseBuildDuration(String[] parts, int offset, String propertyName) throws ParseException {
        String value = sanitizeString(parts, offset, propertyName);

        try {
            return Duration.parse(String.format("PT%s", value));
        } catch (DateTimeParseException ex) {
            throw new ParseException(ex.getMessage(), offset);
        }
    }

    private GeoZone parseGeoZone(String[] parts, int offset, String propertyName) throws ParseException {
        String value = sanitizeString(parts, offset, propertyName);

        GeoZone geoZone = GeoZone.fromString(value);

        if (geoZone == null) {
            throw new ParseException(String.format("%s \"%s\" is invalid", propertyName, value), offset);
        }

        return geoZone;
    }

}
