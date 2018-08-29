package me.orangeflare.openyuri.command;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static me.orangeflare.openyuri.Main.commandIssued;
import static me.orangeflare.openyuri.Main.getResource;

public class animeActions implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if(event.getMessage().getContent().toLowerCase().startsWith("y.kiss")) {
            commandIssued(event, "kiss");
            event.getMessage().delete();

            String[] argArray = event.getMessage().getContent().split(" ", 2);
            if (argArray.length != 2) { return; }
            List<String> args = new ArrayList<>(Arrays.asList(argArray));
            args.remove(0);

            int randImage = ThreadLocalRandom.current().nextInt(1, 6);

            new MessageBuilder()
                    .setContent("Hey " + args.get(0) + "!")
                    .setEmbed(new EmbedBuilder()
                            .setAuthor("OpenYuri")
                            .setThumbnail(getResource("/about/thumbnail.png"))
                            .setTitle(event.getMessage().getAuthor().getDisplayName() + " kissed you!")
                            .setImage(getResource("/kiss/" + randImage + ".gif"), "gif")
                    )
                    .send(event.getChannel());
        }

        if(event.getMessage().getContent().equalsIgnoreCase("y.hug")) {
            commandIssued(event, "hug");

        }

        if(event.getMessage().getContent().equalsIgnoreCase("y.slap")) {
            commandIssued(event, "slap");

        }
    }
}
