package me.orangeflare.openyuri;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.util.logging.ExceptionLogger;
import me.orangeflare.openyuri.command.*;

import java.io.IOException;

public class main {
    public static String version = "v1.0.2-DEV";

    public static void main(String[] args) throws IOException {
        configManager config = new configManager();

        String APIToken = config.read("discordAPI");
        if (APIToken.isEmpty()) {
            System.err.println("No API Token Provided!");
            System.exit(0);
        }

        new DiscordApiBuilder()
                .setToken(APIToken)
                .setRecommendedTotalShards().join()
                .loginAllShards()
                .forEach(shardFuture -> shardFuture
                        .thenAccept(main::about)
                        .exceptionally(ExceptionLogger.get())
                );
    }

    public static void commandInfo(MessageCreateEvent event, String command) {
        System.out.println(event.getMessage().getAuthor() + " issued command '" + command + "'");
    }

    private static void about(DiscordApi yuri) {
        yuri.addMessageCreateListener(new ping());
        yuri.addMessageCreateListener(event -> {
            if (event.getMessage().getContent().equalsIgnoreCase("y.about")) {
                commandInfo(event, "y.about");
                new MessageBuilder()
                        .setEmbed(new EmbedBuilder()
                                .setAuthor("OpenYuri", "https://github.com/OrangeFlare/OpenYuriBot", "https://opensource.org/files/osi_keyhole_300X300_90ppi_0.png")
                                .setThumbnail("https://vignette.wikia.nocookie.net/doki-doki-literature-club/images/a/a1/Yuri_sticker_1.png/revision/latest?cb=20171112094412")
                                .addField("OpenYuri Developer", "OrangeFlare#1337", true)
                                .addField("YuriTheKnifeWaifu Developer", "The Greatest Hero#0001", true)
                                .addField("GitHub", "https://github.com/OrangeFlare/OpenYuriBot", false)
                                .setTitle(version)
                                .setDescription("About Me!"))
                        .send(event.getChannel());
            }
        });
    }
}
