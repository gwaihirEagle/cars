package cz.example.cars.impl;

import cz.example.cars.LocationService;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.*;

class LocationServiceImplTest {

    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
        ;
    }

    @org.junit.jupiter.api.BeforeAll
    public static void beforeClass() {
        LogManager.getRootLogger().debug("\nLocationServiceImplTest----> Initializing tests");

    }

    @org.junit.jupiter.api.AfterAll
    public static void afterClass() {
        LogManager.getRootLogger().debug("---- End of tests --LocationServiceImplTest--");
    }

    @Test
    void load() {
        Properties props = null;

        try {
            props = new LocationServiceImpl().load("resources\\location.properties");
        } catch (CarException e) {
            e.printStackTrace();
        }
        assertEquals("resources\\cars_in.xml", props.getProperty("cars.file.path"));

    }

    // Todo
    @Test
    void loadException() {
        LocationService locationService = new LocationServiceImpl();

    }

}