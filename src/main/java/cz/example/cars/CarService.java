/**
 * CarService.java - Service to create instations of cars according to XML representation
 */
package cz.example.cars;

import java.util.List;

import cz.example.cars.gen.Cars;
import cz.example.cars.model.Car;

/**
 * Service for working whit cars
 */
public interface CarService {

    /**
     * Method creates cars from XML representation.
     *
     * @param cars XML representation
     * @return List of {@link Car}
     */
    List<Car> createCars(Cars cars);
}