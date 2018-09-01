package me.orangeflare.openyuri.command;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import static me.orangeflare.openyuri.Main.commandIssued;

public class utilities implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if(event.getMessage().getContent().toLowerCase().startsWith("y.emojilist") ||
                event.getMessage().getContent().toLowerCase().startsWith("y.emojis")) {
            commandIssued(event, "emojilist");
            event.getChannel().sendMessage(event.getApi().getCustomEmojis().toString());
        }
    }
}
