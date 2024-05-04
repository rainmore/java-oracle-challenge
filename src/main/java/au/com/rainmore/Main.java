package au.com.rainmore;

import au.com.rainmore.domains.CSVRecord;
import au.com.rainmore.services.CSVRecordsService;
import au.com.rainmore.services.csv.CSVRecordLineParsingService;
import au.com.rainmore.services.csv.CSVRecordParsingService;
import au.com.rainmore.services.reports.ReportService;

import java.text.ParseException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide CSV string");
            System.exit(1);
        }

        CSVRecordParsingService csvRecordParsingService = new CSVRecordParsingService(new CSVRecordLineParsingService());
        ReportService reportService = new ReportService(new CSVRecordsService());

        try {
            List<CSVRecord> csvRecords = csvRecordParsingService.parseCSV(args[0]);
            String report = reportService.buildReport(csvRecords);

            System.out.println(report);
            System.exit(0);
        } catch (ParseException ex) {
            System.out.println(String.format("%s at offset %d", ex.getMessage(), ex.getErrorOffset()));
            System.exit(1);
        }
    }

}
