package cz.example.cars.impl;

import cz.example.cars.gen.Cars;
import cz.example.cars.model.Car;
import cz.example.cars.model.ParentCar;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



class CarServiceImplTest {

    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
        ;
    }

    @org.junit.jupiter.api.BeforeAll
    public static void beforeClass() {
        LogManager.getRootLogger().debug("\nCarServiceImplTest----> Initializing tests");

    }

    @org.junit.jupiter.api.AfterAll
    public static void afterClass() {
        LogManager.getRootLogger().debug("---- End of tests --CarServiceImplTest--");
    }


    @Test
    void createCars() {
        Cars cars = new CarDefinitionServiceImpl().load("src\\main\\resources\\cars_in.xml");
        List<Car> ls = new CarServiceImpl().createCars(cars);
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date date = null;

        try {
            assertEquals("Skoda", ((ParentCar)ls.get(0)).getManufacturer());
            assertEquals("Felicia", ls.get(0).getModel());
            assertEquals(230 * 150, ls.get(0).getArea());
            assertEquals(800, ls.get(0).getWeight());
            date = df.parse("1998-01-02");
            assertEquals(date, ls.get(0).getDateOfManufacture());
            assertEquals(false, ls.get(0).isElectric());

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}