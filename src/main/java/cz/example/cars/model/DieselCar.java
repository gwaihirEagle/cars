package cz.example.cars.model;

/**
 * Gasoline car model class
 */
public class DieselCar extends ParentCar {

    public DieselCar(String manufacturer, String model, String className, int length, int width, int weight, String dateManufacture) {

        super(manufacturer,model, className, length, width, weight, dateManufacture, false);

    }

    @Override
    public String toString() {
        return ""+this.getClass().getName()+"||"+super.toString()+
                '}';
    }


}
