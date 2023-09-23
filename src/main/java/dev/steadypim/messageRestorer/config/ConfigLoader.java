package dev.steadypim.messageRestorer.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class ConfigLoader {
    private static final String CONFIG_FILE_PATH = "src/main/resources/application.yml";
    private static final String DATA_URL_PROPERTY = "dataUrl";

    public String getDataUrl() {
        Properties properties = new Properties();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(fis);

            return properties.getProperty(DATA_URL_PROPERTY);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
