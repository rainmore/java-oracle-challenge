package au.com.rainmore.utils;

import java.io.InputStream;
import java.util.function.Supplier;

public class ResourceUtils {

    public static Supplier<InputStream> loadResource(String fileName) {
        return () -> ResourceUtils.class.getClassLoader().getResourceAsStream(fileName);
    }
}
