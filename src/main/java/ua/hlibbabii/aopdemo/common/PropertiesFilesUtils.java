package ua.hlibbabii.aopdemo.common;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

/**
 * Created by hlib on 7/8/14.
 */
@Component
public class PropertiesFilesUtils {
    public void saveProperties(Properties props, String fileName) throws IOException, URISyntaxException {
        URL url = getClass().getClassLoader().getResource(fileName);
        File file = new File(url.toURI().getPath());
        OutputStream outputStream = new FileOutputStream(file);
        props.store(outputStream, "Hi");
    }

    public Properties loadProperties(String fileName) throws IOException {
        Properties props = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
        if(input==null){
            throw new IOException(String.format("Can't find file %s in classpath", fileName));
        }
        props.load(input);
        return props;
    }
}
