package me.orangeflare.openyuri.command.devUtils;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import static me.orangeflare.openyuri.Main.commandIssued;

public class objectInfo implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if(event.getMessage().getContent().toLowerCase().startsWith("y.channelinfo")) {
            commandIssued(event, "channelInfo");
            event.getMessage().delete();

            event.get;
        }
    }
}
