package cz.example.cars.model;

/**
 * Electric car model class
 */
public class ElectricCar extends ParentCar {

    public ElectricCar(String manufacturer, String model, String className, int length, int width, int weight, String dateManufacture) {

        super(manufacturer,model, className, length, width, weight, dateManufacture, true);

    }

    @Override
    public String toString() {
        return ""+this.getClass().getName()+"||"+super.toString()+
                '}';
    }


}
