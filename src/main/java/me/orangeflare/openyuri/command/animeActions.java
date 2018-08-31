package me.orangeflare.openyuri.command;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static me.orangeflare.openyuri.Main.commandIssued;
import static me.orangeflare.openyuri.Main.getResource;

public class animeActions implements MessageCreateListener {
    private static int kissCount = 6;
    private static int slapCount = 10;
    private static int hugCount = 8;
    private static int kickCount = 6;
    private static int punchCount = 8;
    private static int lickCount = 7;

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if(event.getMessage().getContent().toLowerCase().startsWith("y.kiss")) {
            commandIssued(event, "kiss");

            String[] argArray = event.getMessage().getContent().split(" ", 2);
            if (argArray.length != 2) { return; }
            List<String> args = new ArrayList<>(Arrays.asList(argArray));
            args.remove(0);

            int randImage = ThreadLocalRandom.current().nextInt(1, kissCount+1);

            new MessageBuilder()
                    .setContent("Hey " + args.get(0) + "!")
                    .setEmbed(new EmbedBuilder()
                            .setAuthor("OpenYuri")
                            .setThumbnail(getResource("/about/thumbnail.png"))
                            .setTitle(event.getMessage().getAuthor().getDisplayName() + " kissed you!")
                            .setImage(getResource("/kiss/" + randImage + ".gif"), "gif")
                            .setColor(Color.decode("#9c27b0"))
                    )
                    .send(event.getChannel());
        }

        if(event.getMessage().getContent().toLowerCase().startsWith("y.slap")) {
            commandIssued(event, "slap");

            String[] argArray = event.getMessage().getContent().split(" ", 2);
            if (argArray.length != 2) { return; }
            List<String> args = new ArrayList<>(Arrays.asList(argArray));
            args.remove(0);

            int randImage = ThreadLocalRandom.current().nextInt(1, slapCount+1);

            new MessageBuilder()
                    .setContent("Hey " + args.get(0) + "!")
                    .setEmbed(new EmbedBuilder()
                            .setAuthor("OpenYuri")
                            .setThumbnail(getResource("/about/thumbnail.png"))
                            .setTitle(event.getMessage().getAuthor().getDisplayName() + " slapped you!")
                            .setImage(getResource("/slap/" + randImage + ".gif"), "gif")
                            .setColor(Color.decode("#9c27b0"))
                    )
                    .send(event.getChannel());
        }

        if(event.getMessage().getContent().toLowerCase().startsWith("y.hug")) {
            commandIssued(event, "hug");

            String[] argArray = event.getMessage().getContent().split(" ", 2);
            if (argArray.length != 2) { return; }
            List<String> args = new ArrayList<>(Arrays.asList(argArray));
            args.remove(0);

            int randImage = ThreadLocalRandom.current().nextInt(1, hugCount+1);

            new MessageBuilder()
                    .setContent("Hey " + args.get(0) + "!")
                    .setEmbed(new EmbedBuilder()
                            .setAuthor("OpenYuri")
                            .setThumbnail(getResource("/about/thumbnail.png"))
                            .setTitle(event.getMessage().getAuthor().getDisplayName() + " hugged you!")
                            .setImage(getResource("/hug/" + randImage + ".gif"), "gif")
                            .setColor(Color.decode("#9c27b0"))
                    )
                    .send(event.getChannel());
        }

        if(event.getMessage().getContent().toLowerCase().startsWith("y.kick")) {
            commandIssued(event, "kick");

            String[] argArray = event.getMessage().getContent().split(" ", 2);
            if (argArray.length != 2) { return; }
            List<String> args = new ArrayList<>(Arrays.asList(argArray));
            args.remove(0);

            int randImage = ThreadLocalRandom.current().nextInt(1, kickCount+1);

            new MessageBuilder()
                    .setContent("Hey " + args.get(0) + "!")
                    .setEmbed(new EmbedBuilder()
                            .setAuthor("OpenYuri")
                            .setThumbnail(getResource("/about/thumbnail.png"))
                            .setTitle(event.getMessage().getAuthor().getDisplayName() + " kicked you!")
                            .setImage(getResource("/kick/" + randImage + ".gif"), "gif")
                            .setColor(Color.decode("#9c27b0"))
                    )
                    .send(event.getChannel());
        }

        if(event.getMessage().getContent().toLowerCase().startsWith("y.punch")) {
            commandIssued(event, "punch");

            String[] argArray = event.getMessage().getContent().split(" ", 2);
            if (argArray.length != 2) { return; }
            List<String> args = new ArrayList<>(Arrays.asList(argArray));
            args.remove(0);

            int randImage = ThreadLocalRandom.current().nextInt(1, punchCount+1);

            new MessageBuilder()
                    .setContent("Hey " + args.get(0) + "!")
                    .setEmbed(new EmbedBuilder()
                            .setAuthor("OpenYuri")
                            .setThumbnail(getResource("/about/thumbnail.png"))
                            .setTitle(event.getMessage().getAuthor().getDisplayName() + " punched you!")
                            .setImage(getResource("/punch/" + randImage + ".gif"), "gif")
                            .setColor(Color.decode("#9c27b0"))
                    )
                    .send(event.getChannel());
        }

        if(event.getMessage().getContent().toLowerCase().startsWith("y.lick")) {
            commandIssued(event, "lick");

            String[] argArray = event.getMessage().getContent().split(" ", 2);
            if (argArray.length != 2) { return; }
            List<String> args = new ArrayList<>(Arrays.asList(argArray));
            args.remove(0);

            int randImage = ThreadLocalRandom.current().nextInt(1, lickCount+1);

            new MessageBuilder()
                    .setContent("Hey " + args.get(0) + "!")
                    .setEmbed(new EmbedBuilder()
                            .setAuthor("OpenYuri")
                            .setThumbnail(getResource("/about/thumbnail.png"))
                            .setTitle(event.getMessage().getAuthor().getDisplayName() + " licked you!")
                            .setImage(getResource("/lick/" + randImage + ".gif"), "gif")
                            .setColor(Color.decode("#9c27b0"))
                    )
                    .send(event.getChannel());
        }
    }
}
