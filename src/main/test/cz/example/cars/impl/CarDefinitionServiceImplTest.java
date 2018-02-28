package cz.example.cars.impl;

import cz.example.cars.gen.Cars;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarDefinitionServiceImplTest {

    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
        ;
    }

    @org.junit.jupiter.api.BeforeAll
    public static void beforeClass() {
        LogManager.getRootLogger().debug("\n CarDefinitionServiceImplTest----> Initializing tests");

    }

    @org.junit.jupiter.api.AfterAll
    public static void afterClass() {
        LogManager.getRootLogger().debug("---- End of tests --CarDefinitionServiceImplTest--");
    }


    @Test
    void load() {
        Cars cars = new CarDefinitionServiceImpl().load("src\\main\\resources\\cars_in.xml");
        assertEquals("Skoda",cars.getListCars().get(0).getManufacturer());
        assertEquals("Felicia",cars.getListCars().get(0).getModel());
        assertEquals("cz.example.cars.model.GasolineCar",cars.getListCars().get(0).getClassN());
        assertEquals(800,cars.getListCars().get(0).getWeight());
        assertEquals(230,cars.getListCars().get(0).getLength());
        assertEquals(150,cars.getListCars().get(0).getWidth());
        assertEquals("1998-01-02",cars.getListCars().get(0).getDateManufactured());

        assertEquals("Volkswagen",cars.getListCars().get(1).getManufacturer());
        assertEquals("Passat",cars.getListCars().get(1).getModel());
        assertEquals("cz.example.cars.model.DieselCar",cars.getListCars().get(1).getClassN());
        assertEquals(950,cars.getListCars().get(1).getWeight());
        assertEquals(260,cars.getListCars().get(1).getLength());
        assertEquals(170,cars.getListCars().get(1).getWidth());
        assertEquals("1995-10-26",cars.getListCars().get(1).getDateManufactured());

    }
}