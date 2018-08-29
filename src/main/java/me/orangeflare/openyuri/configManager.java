package me.orangeflare.openyuri;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class configManager {
    InputStream inputStream;
    String configProperty;

    public String read(String field) throws IOException {
        try {
            Properties botConfig = new Properties();
            String botConfigName = "bot.properties";

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(botConfigName);

            if (inputStream != null) {
                botConfig.load(inputStream);
            } else {
                throw new FileNotFoundException("[Warning!] Bot configuration file not found!");
            }
            String configProperty = botConfig.getProperty(field);
        } catch (Exception e) {
            System.err.println("[ERROR] " + e);
        } finally {
            inputStream.close();
        }
        return configProperty;
    }
}
