package au.com.rainmore.services;

import au.com.rainmore.domains.CSVRecord;
import au.com.rainmore.services.csv.CSVRecordLineParsingService;
import au.com.rainmore.services.csv.CSVRecordParsingService;
import au.com.rainmore.utils.ResourceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.text.ParseException;
import java.util.List;
import java.util.function.Supplier;

class CSVRecordsServiceTest {

    private CSVRecordsService reportService;

    private CSVRecordParsingService csvRecordParsingService;

    @BeforeEach
    void setUp() {
        this.reportService = new CSVRecordsService();
        this.csvRecordParsingService = new CSVRecordParsingService(new CSVRecordLineParsingService());
    }

    @Test
    void test_countCustomerIdByContractId_returns_expected() {

    }

    @Test
    void test_groupCustomerIdByGeoZone_returns_expected() {

    }

    @Test
    void test_countCustomerIdByGeoZone_returns_expected() {

    }

    @Test
    void test_calculateAveDurationByGeoZone_returns_expected() {

    }


    private List<CSVRecord> getCSVRecords() {
        Supplier<InputStream> inputStream = ResourceUtils.loadResource("test-csv-valid.csv");
        try {
            return csvRecordParsingService.parseCSV(inputStream.get());
        } catch (ParseException ex) {
            // do nothing
            return List.of();
        }
    }

}
