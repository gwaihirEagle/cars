/**
 * LocationService.java - Service serves loading info property from file
 */
package cz.example.cars;


import cz.example.cars.impl.CarException;

import java.util.Properties;

/**
 * Service serves for loading information from property files.
 *
 */
public interface LocationService {

    /**
     * Method loads property file.
     *
     * @param filePath Path to property file
     * @return Property file
     */
    Properties load(String filePath) throws CarException;
}