/**
 * CarDefinitionService.java - Service reads list of cars from xml file including car properties
 */
package cz.example.cars;

import cz.example.cars.gen.Cars;


/**
 * Service serves for loading cars definitions from XML file.
 */
public interface CarsDefinitionService {

    /**
     * Method loads cars definitions.
     *
     * @param xmlFilePath Path to XML file
     * @return {@link Cars}
     */
    Cars load(String xmlFilePath);
}