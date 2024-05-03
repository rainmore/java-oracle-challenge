package au.com.rainmore.services;

import au.com.rainmore.domains.CSVRecord;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CSVRecordParsingService {

    private CSVRecordLineParsingService csvRecordLineParsingService;

    public CSVRecordParsingService(CSVRecordLineParsingService csvRecordLineParsingService) {
        this.csvRecordLineParsingService = csvRecordLineParsingService;
    }

    public List<CSVRecord> parseCSV(InputStream inputStream) throws ParseException {
        List<CSVRecord> csvRecords = new ArrayList<>();
        // TODO
        return csvRecords;
    }

    public List<CSVRecord> parseCSV(String string) throws ParseException {
        List<CSVRecord> csvRecords = new ArrayList<>();
        // TODO
        return csvRecords;
    }

}
