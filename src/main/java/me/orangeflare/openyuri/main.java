package me.orangeflare.openyuri;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        configManager config = new configManager();

        DiscordApi api = new DiscordApiBuilder().setToken(config.read("discordAPI")).login().join();
    }
}
