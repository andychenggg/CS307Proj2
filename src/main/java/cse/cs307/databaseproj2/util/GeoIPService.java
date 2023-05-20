package cse.cs307.databaseproj2.util;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import java.io.File;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.net.InetAddress;
import org.springframework.stereotype.Service;

@Service
public class GeoIPService {

    private static final DatabaseReader dbReader;

    static {
        try {
            dbReader =
                new DatabaseReader.Builder(new File("src/main/resources/database/GeoLite2-City.mmdb")).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public GeoIPService() throws IOException {
//        File database = new File("./src/main/java/resources/database/GeoLite2-City.mmdb");
//        dbReader = new DatabaseReader.Builder(database).build();
//    }

    public static String [] getLocation(String ip)
        throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = dbReader.city(ipAddress);

        String countryName = response.getCountry().getName();
        String cityName = response.getCity().getName();

        return new String[] {countryName, cityName};
    }
}

