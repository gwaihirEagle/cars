package cz.example.cars.impl;

import cz.example.cars.CarService;
import cz.example.cars.gen.Cars;
import cz.example.cars.model.ParentCar;
import cz.example.cars.model.Car;
import org.apache.logging.log4j.LogManager;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


/**
 * Save collection using reflection + shows required statistics
 */
public class CarServiceImpl implements CarService {

    /**
     * reads cars from collection and uses reflection to use car specific class model
     *
     * @param cars .. collection of cars read from xml as ParentCar
     * @return .. interface collection of Car
     */
    @Override
    public List<Car> createCars(Cars cars) {
        List<Car> carsList = new ArrayList<>();

        // reflection object
        Class cls = null;

        //methods parameters
        Class[] cArg = new Class[7];

        cArg[0] = String.class; // setManufacturer
        cArg[1] = String.class; // setModel
        cArg[2] = String.class; // setClassName
        cArg[3] = int.class; // setLength
        cArg[4] = int.class; // setWidth
        cArg[5] = int.class; // setWeight
        cArg[6] = String.class; // setDateOfManufacture

        for (ParentCar parentCar : cars.getListCars()) {
            try {
                cls = Class.forName(parentCar.getClassN());
                Object obj = cls.getDeclaredConstructor(cArg).newInstance(parentCar.getManufacturer(),
                        parentCar.getModel(),parentCar.getClassN(), parentCar.getLength(), parentCar.getWidth(),
                        parentCar.getWeight(), parentCar.getDateManufactured());
                carsList.add((Car) obj);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                    NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();

                throw new CarException("Error in creating car when using reflection");
            }


        }

        if (cls == null) {
            throw new CarException("No cars to be added for input: '" + cars.getListCars() + "'");
        }


        return carsList;

    }

    /**
     * Informational print of required statistics
     *
     * @param cars
     */
    public void printCars(List<Car> cars) {
        this.printNumberOfCars(cars);
        this.printOrderedAreaCars(cars);
        this.printDifferenceAgeCars(cars);
        this.printGroupedCarName(cars);
        this.averageWeight(cars);

    }

    /**
     * Show number of cars in collection
     *
     * @param cars
     */
    public void printNumberOfCars(List<Car> cars) {
        LogManager.getRootLogger().debug("Read collection - using dynamic reflection");
        System.out.println("--- Number of cars ----------------------------------");
        System.out.println(cars.size());
    }

    /**
     * Show list of cars ordered by area
     *
     * @param cars
     */
    public void printOrderedAreaCars(List<Car> cars) {
        System.out.println("--- Area sort ------------------------------------");
        Collections.sort(cars, ParentCar.areaComparator); // sort by area

        for (Car car : cars) {
            System.out.println("" + car);
        }


    }

    /**
     * Show the difference youngest - oldest manufactured car in days..
     *
     * @param cars
     */
    public void printDifferenceAgeCars(List<Car> cars) {
        Collections.sort(cars, ParentCar.dateCreationComparator); // sort by date of manufacture
        Date youngest = cars.get(0).getDateOfManufacture();
        Date oldest = cars.get(cars.size() - 1).getDateOfManufacture();
        System.out.println("--- Days between the youngest and the oldest car ----");
        int days = (int) ((oldest.getTime() - youngest.getTime()) / (1000 * 60 * 60 * 24));
        System.out.println("" + days);
    }


    /**
     * Print statistics - cars by name by occurence of presence
     *
     * @param cars
     */
    public void printGroupedCarName(List<Car> cars) {
        System.out.println("--- Group by car type -------------------------------");
        Map<String, Integer> hashMap = new HashMap<>();
        for (Car car : cars) {
            String className = car.getClass().getName();
            if (hashMap.containsKey(className)) {
                int actualValue = hashMap.get(className).intValue();
                hashMap.put(className, ++actualValue);

            } else { // pair: key,value does not exist..
                hashMap.put(className, new Integer(1));
            }
        }
        System.out.println(hashMap);
    }

    /**
     * Print average weight of cars
     *
     * @param cars
     */
    public void averageWeight(List<Car> cars) {
        System.out.println("--- Average weight of all cars ----------------------");
        int weightAgregated = 0;

        for (Car car : cars) {
            weightAgregated = weightAgregated + car.getWeight();
        }
        System.out.println(weightAgregated / cars.size());

    }


}
