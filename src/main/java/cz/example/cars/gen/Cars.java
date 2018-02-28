package cz.example.cars.gen;

import cz.example.cars.model.ParentCar;

import org.apache.logging.log4j.LogManager;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Unmarshalling xml
 */
@XmlRootElement(name = "cars")
public class Cars {

    List<ParentCar> listCars;

    @XmlElement(name = "car")
    public List<ParentCar> getListCars() {
        return listCars;
    }

    public void setListCars(List<ParentCar> listCars) {
        this.listCars = listCars;
    }

    public void printCars() {
        LogManager.getRootLogger().debug("Read collection - unmarshall from xml to ParentCar collection");
        for(ParentCar parentCar: listCars) {
            LogManager.getRootLogger().debug(""+parentCar);
        }
    }
}