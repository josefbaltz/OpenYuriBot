package me.orangeflare.openyuri;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.util.logging.ExceptionLogger;
import me.orangeflare.openyuri.command.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    private static String version = "v1.0.3-DEV";

    public static void main(String[] args) {
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
                        .thenAccept(Main::core)
                        .exceptionally(ExceptionLogger.get())
                );
    }

    public static void commandIssued(MessageCreateEvent event, String command) {
        System.out.println(event.getMessage().getAuthor() + " issued command '" + command + "'");
    }

    public static BufferedImage getResource(String dir) {
        BufferedImage resource;
        try {
            resource = ImageIO.read(Main.class.getResource(dir));
            return resource;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void core(DiscordApi yuri) {
        yuri.addMessageCreateListener(new ping());
        yuri.addMessageCreateListener(new flipCoin());
        yuri.addMessageCreateListener(new animeActions());
        yuri.addMessageCreateListener(event -> {
            if (event.getMessage().getContent().equalsIgnoreCase("y.help") ||
                    event.getMessage().getContent().equalsIgnoreCase("y.commands")) {
                commandIssued(event, "help");
                new MessageBuilder()
                        .append("``OpenYuri Bot - " + version + "``\n")
                        .append("``y.help, y.commands - Displays this text!``\n")
                        .append("``y.about - Give information about me!``\n")
                        .append("``y.ping - Replies with 'Pong!'``\n")
                        .append("``y.flipcoin, y.coinflip - Flips a coin!``")
                        .send(event.getChannel());
            }
        });
        yuri.addMessageCreateListener(event -> {
            if (event.getMessage().getContent().equalsIgnoreCase("y.about")) {
                commandIssued(event, "about");
                new MessageBuilder()
                        .setEmbed(new EmbedBuilder()
                                .setAuthor("OpenYuri", "https://github.com/OrangeFlare/OpenYuriBot", getResource("about/icon.png"))
                                .setThumbnail(getResource("about/thumbnail.png"))
                                .setTitle(version)
                                .setDescription("About Me!")
                                .addField("OpenYuri Developer", "OrangeFlare#1337", true)
                                .addField("YuriTheKnifeWaifu Developer", "The Greatest Hero#0001", true)
                                .addField("GitHub", "https://github.com/OrangeFlare/OpenYuriBot", false)
                        )
                        .send(event.getChannel());
            }
        });
    }
}
