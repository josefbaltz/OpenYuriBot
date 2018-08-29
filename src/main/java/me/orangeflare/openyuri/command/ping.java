package me.orangeflare.openyuri.command;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import static me.orangeflare.openyuri.main.commandInfo;

public class ping implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if(event.getMessage().getContent().equalsIgnoreCase("y.ping")) {
            commandInfo(event, "y.ping");
            event.getChannel().sendMessage("Pong!");
        }
    }
}
