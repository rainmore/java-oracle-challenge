package au.com.rainmore.services.csv;

import au.com.rainmore.domains.CSVRecord;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CSVRecordParsingService {

    private final CSVRecordLineParsingService csvRecordLineParsingService;

    public CSVRecordParsingService(CSVRecordLineParsingService csvRecordLineParsingService) {
        this.csvRecordLineParsingService = csvRecordLineParsingService;
    }

    public List<CSVRecord> parseCSV(String string) throws ParseException {
        List<CSVRecord> csvRecords = new ArrayList<>();
        String[] lines = string.split(System.lineSeparator());

        for (String line : lines) {
            csvRecords.add(csvRecordLineParsingService.fromCSVLine(line));
        }

        return csvRecords;
    }

    public List<CSVRecord> parseCSV(String[] args) throws IllegalArgumentException, ParseException {
        if (args.length != 1 || args[0].trim().isEmpty()) {
            throw new IllegalArgumentException("Please provide CSV string");
        }
        return parseCSV(args[0]);
    }

}
