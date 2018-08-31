package me.orangeflare.openyuri.command;

import me.orangeflare.openyuri.configManager;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.orangeflare.openyuri.Main.commandIssued;

public class simonSays implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if(event.getMessage().getContent().toLowerCase().startsWith("y.ss") ||
                event.getMessage().getContent().toLowerCase().startsWith("y.simonsays")) {
            commandIssued(event, "simonSays");
            configManager config = new configManager();
            String DiscordOwnerID;
            DiscordOwnerID = config.read("ownerID");
            if (DiscordOwnerID.isEmpty()) {
                System.err.println("No Owner ID Provided!");
            }
            Long DiscordOwnerIDLong = Long.parseLong(DiscordOwnerID);

            if (event.getMessage().getAuthor().getId() != DiscordOwnerIDLong) { return; }

            String[] argArray = event.getMessage().getContent().split(" ", 2);
            if (argArray.length != 2) { return; }

            event.getMessage().delete();

            List<String> args = new ArrayList<>(Arrays.asList(argArray));
            args.remove(0);

            new MessageBuilder()
                    .setContent(args.get(0))
                    .send(event.getChannel());
        }
    }
}
