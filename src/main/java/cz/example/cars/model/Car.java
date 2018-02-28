package cz.example.cars.model;

import java.util.Date;

/**
 * Define behaviour common for all different cars
 *
 * @author Mark
 * @since 1.0.0
 */
public interface Car {

    String getModel();

    int getArea();

    int getWeight();

    Date getDateOfManufacture();

    boolean isElectric();

}