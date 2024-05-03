package au.com.rainmore.domains;

import java.util.Arrays;

public enum GeoZone {

    US_EAST,
    US_WEST,
    EU_WEST
    // TODO to add the rest fields
    ;

    public static GeoZone fromString(String geoZoneString) {
        return Arrays.stream(GeoZone.values())
                .filter(geoZone -> geoZone.name().equalsIgnoreCase(geoZoneString))
                .findFirst()
                // TODO to confirm if `.orElseThrow();` can be used to avoid the usage of `null
                .orElse(null);
    }

}
