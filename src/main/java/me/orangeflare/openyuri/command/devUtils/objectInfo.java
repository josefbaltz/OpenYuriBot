package me.orangeflare.openyuri.command.devUtils;

import org.javacord.api.entity.Nameable;
import org.javacord.api.entity.channel.ServerChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.Collections;
import java.util.stream.Collectors;

import static me.orangeflare.openyuri.Main.commandIssued;

public class objectInfo implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String formattedContent = event.getMessage().getContent().toLowerCase();

        //TODO Optimize this Code
        if(formattedContent.startsWith("y.channelinfo")) {
            commandIssued(event, "channelInfo");
            /*
            new MessageBuilder()
                    .append(event.getMessage().getChannel().asServerChannel().map(ServerChannel::getName).get() + "\n")
                    .append(event.getMessage().getChannel().asServerChannel().map(ServerChannel::getIdAsString).get())
                    .send(event.getChannel());
            */
            new MessageBuilder()
                    .setEmbed(new EmbedBuilder()
                            .setAuthor("OpenYuri"))

        }

        if(formattedContent.startsWith("y.emojilist") || formattedContent.startsWith("y.emojis")) {
            commandIssued(event, "emojiList");
            new MessageBuilder()
                    .append("```" + event.getServer().map(Server::getName).get() + "'s Emoji List\n - ")
                    .append(event.getServer()
                            .map(Server::getCustomEmojis)
                            .orElse(Collections.emptyList())
                            .stream()
                            .map(Nameable::getName)
                            .collect(Collectors.joining("\n")))
                    .append("```")
                    .send(event.getChannel());
        }
    }
}
