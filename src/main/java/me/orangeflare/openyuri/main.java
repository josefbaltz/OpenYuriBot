package me.orangeflare.openyuri;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        configManager config = new configManager();

        String APIToken = config.read("discordAPI");

        DiscordApi api = new DiscordApiBuilder().setToken(APIToken).login().join();
    }
}
