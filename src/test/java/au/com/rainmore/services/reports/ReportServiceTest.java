package au.com.rainmore.services.reports;

import au.com.rainmore.domains.CSVRecord;
import au.com.rainmore.services.CSVRecordsService;
import au.com.rainmore.utils.features.CSVRecordFeatures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReportServiceTest {

    private ReportService reportService;

    private CSVRecordFeatures csvRecordFeatures;

    @BeforeEach
    void setUp() {
        reportService = new ReportService(new CSVRecordsService());
        csvRecordFeatures = new CSVRecordFeatures();
    }

    @Test
    void test_buildReport_return_expectedString() {
        List<CSVRecord> csvRecords = csvRecordFeatures.getValidCSVRecords();

        String report = reportService.buildReport(csvRecords);
        String expected = """
                The number of unique customerId for contractId 2345 is 3
                The number of unique customerId for contractId 2346 is 2

                The number of unique customerId for geozone \"us_east\" is 1
                The number of unique customerId for geozone \"us_west\" is 2
                The number of unique customerId for geozone \"eu_west\" is 2

                The average buildduration for geozone \"us_east\" is 3445s
                The average buildduration for geozone \"us_west\" is 2216s
                The average buildduration for geozone \"eu_west\" is 4222s

                The list of unique customerId for geozone \"us_east\" is (2343225)
                The list of unique customerId for geozone \"us_west\" is (1223456, 1233456)
                The list of unique customerId for geozone \"eu_west\" is (3244132, 3244332)
                """.trim();

        assertEquals(expected, report);
    }

    @Test
    void test_buildReport_return_expectedString_withEmptyCSVRecords() {
        List<CSVRecord> csvRecords = List.of();

        String report = reportService.buildReport(csvRecords);
        String expected = "";

        assertEquals(expected, report);
    }

}
