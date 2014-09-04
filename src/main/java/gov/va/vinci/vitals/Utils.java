package gov.va.vinci.vitals;

import groovy.util.ConfigObject;
import groovy.util.ConfigSlurper;

//import com.gentlyweb.utils.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

/**
 * Utility methods
 *
 */
public class Utils {

    /**
     * Load the config file - From code by Ryan Cornia
     *
     * @param environment
     * @param filePaths
     * @return
     * @throws IOException
     */
    public static ConfigObject loadConfigFile(String environment, String... filePaths) throws IOException {
        ConfigSlurper slurper = new ConfigSlurper(environment);
        ConfigObject config = new ConfigObject();

        ClassLoader cl = ClassLoader.getSystemClassLoader();

        for (String filePath : filePaths) {
            InputStream in = null;
            in = cl.getResourceAsStream(filePath);
            System.out.println("Loading file:  " + new File(filePath).getAbsolutePath());
            
            String resourceAsString = IOUtils.toString(in);
            config.merge(slurper.parse(resourceAsString));
            System.out.println("Loaded file:  " + new File(filePath).getAbsolutePath());
        }
        return config;
    }

}
