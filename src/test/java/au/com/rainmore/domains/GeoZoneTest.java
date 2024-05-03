package au.com.rainmore.domains;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoZoneTest {

    @Test
    void test_fromString_returnsGeoZone() {
        String lowerCaseString = "us_east";
        GeoZone geoZoneByLowerCaseString = GeoZone.fromString(lowerCaseString);

        assertNotNull(geoZoneByLowerCaseString);
        assertEquals(GeoZone.US_EAST, geoZoneByLowerCaseString);

        String upperCaseString = "US_WEST";
        GeoZone geoZoneByUpperCaseString = GeoZone.fromString(upperCaseString);

        assertNotNull(geoZoneByUpperCaseString);
        assertEquals(GeoZone.US_WEST, geoZoneByUpperCaseString);

        String mixedCasesString = "EU_west";
        GeoZone geoZoneByMixedCasesString = GeoZone.fromString(mixedCasesString);

        assertNotNull(geoZoneByMixedCasesString);
        assertEquals(GeoZone.EU_WEST, geoZoneByMixedCasesString);
    }

    @Test
    void test_fromString_returnsNull() {
        String testString = "us-east-1";
        GeoZone geoZone = GeoZone.fromString(testString);

        assertNull(geoZone);
    }

}
