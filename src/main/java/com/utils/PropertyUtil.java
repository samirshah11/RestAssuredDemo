package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

public class PropertyUtil {

    public static Hashtable<String, String> propertyMap = new Hashtable<>();
    private static Properties properties;
       public static Hashtable<String, String> loadProperties() {

        properties = new Properties();
        try {
            InputStream fileInputStream = PropertyUtil.class.getClassLoader().getResourceAsStream("config/config.properties");
            properties.load(fileInputStream);
            for (String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                propertyMap.put(key, value);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

     return propertyMap;
    }

    public static String getPropertyValue(String key){

        properties = new Properties();
        try {
            InputStream fileInputStream = PropertyUtil.class.getClassLoader().getResourceAsStream("config/config.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return  properties.getProperty(key);
    }
}
