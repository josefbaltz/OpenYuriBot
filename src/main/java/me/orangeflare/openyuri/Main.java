package me.orangeflare.openyuri;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.util.logging.ExceptionLogger;
import me.orangeflare.openyuri.command.*;
import me.orangeflare.openyuri.command.devUtils.*;

import java.awt.*;
import java.io.InputStream;

public class Main {
    private static String version = "v1.3.0-dev";

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
        System.out.println(event.getMessage().getAuthor().asUser().map(User::getDiscriminatedName).get() + " issued command '" + command + "'");
        event.getMessage().delete();
    }

    public static InputStream getResource(String dir) { return Main.class.getResourceAsStream(dir); }

    private static void core(DiscordApi yuri) {
        yuri.updateActivity(ActivityType.LISTENING, "your heartbeat");
        yuri.addMessageCreateListener(new ping());
        yuri.addMessageCreateListener(new flipCoin());
        yuri.addMessageCreateListener(new animeActions());
        yuri.addMessageCreateListener(new simonSays());
        yuri.addMessageCreateListener(new objectInfo());
        yuri.addMessageCreateListener(event -> {
            if (event.getMessage().getContent().equalsIgnoreCase("y.help") ||
                    event.getMessage().getContent().equalsIgnoreCase("y.commands")) {
                commandIssued(event, "help");

                new MessageBuilder()
                        .setEmbed(new EmbedBuilder()
                                .setAuthor("OpenYuri")
                                .setThumbnail(getResource("/about/thumbnail.png"))
                                .setTitle("Help")
                                .setDescription("About My Commands!")
                                .addField("y.help, y.commands", "Displays this text!", false)
                                .addField("y.about, y.info", "Gives information about me!", false)
                                .addField("y.ping", "Replies with 'Pong!'", false)
                                .addField("y.flipcoin, y.coinflip", "Flips a coin!", false)
                                .addField("y.hug, y.kiss, y.slap, y.punch, y.kick, y.lick", "Performs the action on someone else! (Please supply some text afterwards, it can be a user or just text!)", false)
                                .addField("y.emojilist, y.emojis", "Returns a list of the server's custom emojis!", false)
                                .addField("y.channelinfo", "Returns information about the current channel!", false)
                                .addField("y.memberlist, y.members", "Returns the list of members in the current server!", false)
                                .addField("y.serverinfo", "Returns a MASSIVE list of information about the server!", false)
                                .setColor(Color.decode("#9c27b0"))
                        )
                        .send(event.getChannel());
            }
        });
        yuri.addMessageCreateListener(event -> {
            if (event.getMessage().getContent().equalsIgnoreCase("y.about") ||
                    event.getMessage().getContent().equalsIgnoreCase("y.info")) {
                commandIssued(event, "about");

                new MessageBuilder()
                        .setEmbed(new EmbedBuilder()
                                .setAuthor("OpenYuri")
                                .setThumbnail(getResource("/about/thumbnail.png"))
                                .setTitle(version)
                                .setDescription("About Me!")
                                .addField("OpenYuri Developer", "OrangeFlare#1337", true)
                                .addField("YuriTheKnifeWaifu Developer", "The Greatest Hero#0001", true)
                                .addField("GitHub", "OrangeFlare/OpenYuriBot", true)
                                .addField("Server Count", Integer.toString(event.getApi().getServers().size()), true)
                                .setColor(Color.decode("#9c27b0"))
                        )
                        .send(event.getChannel());
            }
        });
        yuri.addMessageCreateListener(event -> {
            if (event.getMessage().getContent().equalsIgnoreCase("y.orangeflare")) {
                commandIssued(event, "orangeflare");

                new MessageBuilder()
                        .setEmbed(new EmbedBuilder()
                                .setTitle("OrangeFlare")
                                .setDescription("About Me!")
                                .addField("Discord", "OrangeFlare#1337", true)
                                .addField("GitHub", "https://github.com/OrangeFlare", true)
                                .setImage(getResource("/etc/orangeflare.gif"), "gif")
                                .setColor(Color.decode("#9c27b0"))
                        )
                        .send(event.getChannel());
            }
        });
    }
}
