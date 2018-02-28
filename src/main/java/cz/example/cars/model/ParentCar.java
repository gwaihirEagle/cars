package cz.example.cars.model;

import cz.example.cars.impl.CarException;
import org.apache.logging.log4j.LogManager;

import javax.xml.bind.annotation.XmlElement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;



/**
 * Parent class - general template to be inherited by child classes> ElectricCar,...
 *
 * */
public class ParentCar implements Car {
    /**
     * Car manufacturer
     */
    @XmlElement(name = "manufacturer")
    private String manufacturer;

    /**
     * Car model
     */
    @XmlElement(name = "model")
    private String model;

    /**
     * Car specific subclass name
     */
    @XmlElement(name = "class")
    private String classN;

    /**
     * Car length
     */
    @XmlElement(name = "length")
    private int length;

    /**
     * Car width
     */
    @XmlElement(name = "width")
    private int width;

    /**
     * Car weight
     */
    @XmlElement(name = "weight")
    private int weight;

    /**
     * Car date of manufacture .. temporary as String
     */
    @XmlElement(name = "dateOfManufacture")
    private String dateManufactured;

    /**
     * Car date converted to Date as required
     */
    private Date dateOfManufacture;

    /**
     * status: is/is not electric
     */
    private boolean isElectric;


    public ParentCar() {

    }


    public ParentCar(String manufacturer, String model, String className, int length, int width, int weight, String dateManufactured,
                      boolean isElectric) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.classN = className;
        this.length = length;
        this.width = width;
        this.weight = weight;
        this.dateManufactured = dateManufactured;

        // car specific properties set in the child super constructor
        this.isElectric = isElectric;
    }

    /**
     * Converts String to Date format
     * @param strIn .. excpected format yyyy-mm-dd
     */
    private Date parseDate(String strIn) {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        Date date = null;
        try {
            date = df.parse(strIn);

        } catch (ParseException e) {
           throw new CarException("Date created: '" + strIn + "' cannot be converted to Date");
        }

        return date;
    }


    /**
     * Comparator to sort cars by area
     */
    public static Comparator<Car> areaComparator = new Comparator<Car>() {

        public int compare(Car c1, Car c2) {
            int carArea1 = ((ParentCar)c1).getArea();
            int carArea2 = ((ParentCar)c2).getArea();

            //ascending order
            return carArea1 - carArea2;

        }};


    /**
     * Comparator to sort cars by dateOfManufacture - to find youngest and oldest model..
     */
    public static Comparator<Car> dateCreationComparator = new Comparator<Car>() {

        public int compare(Car c1, Car c2) {
            Date carDate1 = ((ParentCar)c1).getDateOfManufacture();
            Date carDate2 = ((ParentCar)c2).getDateOfManufacture();

            //ascending order
            return carDate1.compareTo(carDate2);

        }};


    /// getter + toString

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public String getModel() {
        return model;
    }

    public String getClassN() {
        return classN;
    }

    @Override
    public int getArea() {
        return width*length;
    }


    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    public String getDateManufactured() {
        return dateManufactured;
    }

    public Date getDateOfManufacture() {
        return parseDate(dateManufactured);
    }

    @Override
    public boolean isElectric() {
        return isElectric;
    }

    @Override
    public String toString() {
        return "" + "ParentCar" +
                "{" +
                "getManufacturer()='" + getManufacturer() + '\'' +
                ", getModel()='" + getModel() + '\'' +
                ", getArea()='" + getArea() + '\'' +
                ", getWeight()=" + getWeight() +
                ", getDateManufactured()=" + getDateManufactured() +
                ", isElectric()=" + isElectric() +
                '}';
    }


}
