package me.orangeflare.openyuri.command;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import static me.orangeflare.openyuri.Main.commandIssued;

public class ping implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String formattedContent = event.getMessage().getContent().toLowerCase();

        if(formattedContent.equalsIgnoreCase("y.ping")) {
            commandIssued(event, "ping");
            event.getChannel().sendMessage("Pong!");
        }
    }
}
