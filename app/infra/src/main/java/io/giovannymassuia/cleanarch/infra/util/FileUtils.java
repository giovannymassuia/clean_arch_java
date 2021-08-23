package io.giovannymassuia.cleanarch.infra.util;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtils {

    public static String readFileFromResource(String file) {
        try {
            return Resources.toString(Resources.getResource(file), Charsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Fail to read file");
        }
    }

    public static Properties getProperties(String file) {
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream(file);
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new IllegalStateException("Fail to read property file");
        }
    }

}
