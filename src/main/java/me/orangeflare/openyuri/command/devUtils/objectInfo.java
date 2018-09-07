package me.orangeflare.openyuri.command.devUtils;

import org.javacord.api.entity.Nameable;
import org.javacord.api.entity.channel.*;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.util.logging.ExceptionLogger;

import javax.naming.Name;
import java.awt.*;
import java.util.Collections;
import java.util.stream.Collectors;

import static me.orangeflare.openyuri.Main.commandIssued;
import static me.orangeflare.openyuri.Main.getResource;

public class objectInfo implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String formattedContent = event.getMessage().getContent().toLowerCase();

        if(formattedContent.startsWith("y.channelinfo")) {
            commandIssued(event, "channelInfo");

            TextChannel channel = event.getMessage().getChannel();

            String topicContent;
            if (channel.asServerTextChannel().map(ServerTextChannel::getTopic).get().isEmpty()) {
                topicContent = "None";
            } else {
                topicContent = channel.asServerTextChannel().map(ServerTextChannel::getTopic).get();
            }

            new MessageBuilder()
                    .setEmbed(new EmbedBuilder()
                            .setThumbnail(getResource("/about/thumbnail.png"))
                            .setTitle("Channel Information")
                            .addField("Name", channel.asServerChannel().map(ServerChannel::getName).get(), true)
                            .addField("ID", channel.asServerChannel().map(ServerChannel::getIdAsString).get(), true)
                            .addField("Category", channel.asServerTextChannel().flatMap(Categorizable::getCategory).map(Nameable::getName).orElse("None"), true)
                            .addField("Topic", topicContent, true)
                            .setColor(Color.decode("#9c27b0"))
                    )
                    .send(event.getChannel()).exceptionally(ExceptionLogger.get());
        }

        if(formattedContent.startsWith("y.emojilist") || formattedContent.startsWith("y.emojis")) {
            commandIssued(event, "emojiList");
            new MessageBuilder()
                    .append("```" + event.getServer().map(Server::getName).get() + "'s Emoji List\n")
                    .append(event.getServer()
                            .map(Server::getCustomEmojis)
                            .orElse(Collections.emptyList())
                            .stream()
                            .map(Nameable::getName)
                            .collect(Collectors.joining("\n")))
                    .append("```")
                    .send(event.getChannel());
        }

        if(formattedContent.startsWith("y.memberlist") || formattedContent.startsWith("y.members")) {
            commandIssued(event, "memberList");
            new MessageBuilder()
                    .append("```" + event.getServer().map(Server::getName).get() + "'s Member List \n")
                    .append(event.getServer()
                            .map(Server::getMembers)
                            .orElse(Collections.emptyList())
                            .stream()
                            .map(Nameable::getName)
                            .collect(Collectors.joining("\n")))
                    .append("```")
                    .send(event.getChannel());
        }

        if(formattedContent.startsWith("y.serverinfo")) {
            commandIssued(event, "serverInfo");
            new MessageBuilder()
                    .setEmbed(new EmbedBuilder()
                            .setTitle("Server Information")
                            .setThumbnail(getResource("/about/thumbnail.png"))
                            .addField("Name", event.getServer().map(Server::getName).get(), true)
                            .addField("Owner", event.getServer().map(Server::getOwner).map(User::getName).get(), true)
                            .addField("Region", event.getServer().map(Server::getRegion).map(Nameable::getName).get(), true)
                            .addField("ID", event.getServer().map(Server::getId).get().toString(), true)
                            .addField("Voice Channel Count", Integer.toString(event.getServer().map(Server::getVoiceChannels).get().size()), true)
                            .addField("Text Channel Count", Integer.toString(event.getServer().map(Server::getTextChannels).get().size()), true)
                            .addField("Roles", event.getServer().map(Server::getRoles).orElse(Collections.emptyList()).stream().map(Nameable::getName).collect(Collectors.joining(", ")), true)
                            .setColor(Color.decode("#9c27b0"))
                    )
                    .send(event.getChannel());

        }
    }
}
