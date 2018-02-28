package cz.example.cars.impl;

import org.apache.logging.log4j.LogManager;

/**
 * Cast exceptions
 */
public class CarException extends RuntimeException {

    public CarException(String message) {

        super(message);
        LogManager.getRootLogger().error(message);
    }
}
