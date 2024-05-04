package au.com.rainmore.services.csv;

import au.com.rainmore.domains.CSVRecord;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVRecordParsingService {

    private final CSVRecordLineParsingService csvRecordLineParsingService;

    public CSVRecordParsingService(CSVRecordLineParsingService csvRecordLineParsingService) {
        this.csvRecordLineParsingService = csvRecordLineParsingService;
    }

    public List<CSVRecord> parseCSV(InputStream inputStream) throws ParseException {
        List<CSVRecord> csvRecords = new ArrayList<>();
        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                csvRecords.add(csvRecordLineParsingService.fromCSVLine(line));
            }
        }

        return csvRecords;
    }

    public List<CSVRecord> parseCSV(String string) throws ParseException {
        List<CSVRecord> csvRecords = new ArrayList<>();
        String[] lines = string.split(System.lineSeparator());

        for (String line : lines) {
            csvRecords.add(csvRecordLineParsingService.fromCSVLine(line));
        }

        return csvRecords;
    }

}
