
package cz.example.cars.impl;

import cz.example.cars.CarsDefinitionService;
import cz.example.cars.gen.Cars;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * JAXB unmarshall xml --> object
 */
public class CarDefinitionServiceImpl implements CarsDefinitionService {

    /**
     * Unmarshall xml to ParentCar collection
     *
     * @param xmlFilePath path to XML file
     * @return .. ParentCar collection
     */
    @Override
    public Cars load(String xmlFilePath) {
        Cars cars = null;

        try {
            //1. We need to create JAXContext instance
            JAXBContext jc = JAXBContext.newInstance(Cars.class); // Cars .. ObjectFactory

            //2. Use JAXBContext instance to create the Unmarshaller.
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            File xml = new File(xmlFilePath);
            cars = (Cars) unmarshaller.unmarshal(xml);

        } catch (JAXBException e) {
            throw new CarException("Error unmarshalling xml : '" + xmlFilePath);

        }

        return cars;
    }

}
