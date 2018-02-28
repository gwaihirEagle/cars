package cz.example.cars.impl;

import cz.example.cars.gen.Cars;
import cz.example.cars.model.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Properties;

/**
 * run app
 */
public class Application implements Runnable {

    /**
     * File with the path to xml file
     */
    private static String PROPERTYFILE = "resources/location.properties";
    
    /**
     * property file delimiter to divide key, value
     */
    private static String PROPDELIM = "=<";

    /**
     * Property key to save path to input xml
     */
    private static String PROPKEY = "cars.file.path";


    /**
     * for logging purposes - to be able to distinguish levels of debug/error messages
     */
    private static final Logger log = LogManager.getRootLogger();

    /**
     * Thread control
     */
    private Thread showThread = null;


    public static void main(String[] args) {

        Application application = new Application();
        application.start();
        System.out.println("--- Main: Finish -------------------------------------------");

    }

    /**
     * start thread
     */
    public void start() {
        showThread = new Thread(this);
        showThread.start();

        try {
            showThread.join(); // return control to main thread to print finished there
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new CarException("Error interrupted exception'");
        }

    }

    /**
     * Thread application logic
     */
    @Override
    public void run() {
        Properties props;
        Cars cars;

        try {
            props = new LocationServiceImpl().load(Application.PROPERTYFILE);
            cars = new CarDefinitionServiceImpl().load(props.getProperty(Application.getPROPKEY()));
            cars.printCars(); // for debug reasons

            CarServiceImpl ssi = new CarServiceImpl();
            List<Car> carsList = ssi.createCars(cars);

            ssi.printNumberOfCars(carsList);
            System.out.println("[Thread: Going to sleep 1s..]");
            Thread.sleep(1000);
            ssi.printOrderedAreaCars(carsList);
            System.out.println("[Thread: Going to sleep 1s..]");
            Thread.sleep(1000);
            ssi.printDifferenceAgeCars(carsList);
            System.out.println("[Thread: Going to sleep 1s..]");
            Thread.sleep(1000);
            ssi.printGroupedCarName(carsList);
            System.out.println("[Thread: Going to sleep 1s..]");
            Thread.sleep(1000);
            ssi.averageWeight(carsList);
            System.out.println("[Thread: Going to sleep 1s..]");
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new CarException("Error interrupted Exception");

        }

    }

    public static String getPROPDELIM() {
        return PROPDELIM;
    }

    public static String getPROPKEY() {
        return PROPKEY;
    }

    public static String getPROPERTYFILE() {
        return PROPERTYFILE;
    }
}
