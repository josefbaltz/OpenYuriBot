package me.orangeflare.openyuri;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class configManager {
    Properties botProp = new Properties();
    FileInputStream input;

    public String read(String field) {
        try {
            input = new FileInputStream("./bot.properties");
            botProp.load(input);
        } catch (IOException e) {
            System.out.println("No bot.properties file found!\nGenerating one for you now ...");
            String data = "#OpenYuri Configuration File\n" +
                    "discordAPI=";
            try {
                Files.write(Paths.get("./bot.properties"), data.getBytes());
            } catch (IOException e0) {
                System.err.println(e0);
            }
            System.out.println("Done, please edit the file before starting the bot again!");
            System.exit(0);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e1) {
                    System.err.println(e1);
                }
            }
        }
        return botProp.getProperty(field);
    }
}
