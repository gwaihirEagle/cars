package cz.example.cars.impl;

import cz.example.cars.LocationService;
import org.apache.logging.log4j.LogManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * HasMap property - xml filename fill
 */
public class LocationServiceImpl implements LocationService {

    /**
     * Reads xml filepath from property file
     * @param filePath Path to property file
     * @return .. hasmap with property
     * @throws CarException
     */
    @Override
    public Properties load(String filePath) throws CarException {
        Properties props = null;


        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(),Application.getPROPDELIM());

            String key = stringTokenizer.nextToken();
            String value = stringTokenizer.nextToken();
            value = value.substring(0, (value.length()-1)); // delete last char ">"

            props = new Properties();
            props.setProperty(key,value);
            LogManager.getRootLogger().debug("Property read: "+props.stringPropertyNames());

            bufferedReader.close();

        }
        catch (IOException e) {
            //LoggerClass.getLogger().error(" Error reading file: " + filePath + " ,file has to be in resources directory");
            throw new CarException("Error reading file: '" + filePath + "' ,file has to be in: '" + Application.getPROPERTYFILE() + "' directory");
        }


        return props;

    }
}
