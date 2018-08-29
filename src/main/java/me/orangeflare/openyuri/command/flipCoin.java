package me.orangeflare.openyuri.command;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.concurrent.ThreadLocalRandom;

import static me.orangeflare.openyuri.Main.commandIssued;

public class flipCoin implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessage().getContent().equalsIgnoreCase("y.flipcoin") || event.getMessage().getContent().equalsIgnoreCase("y.coinflip")) {
            commandIssued(event, "flipcoin");
            event.getMessage().delete();

            int coinFlipInt = ThreadLocalRandom.current().nextInt(0,2);

            if (coinFlipInt==0) {
                event.getChannel().sendMessage("Heads!");
            } else {
                event.getChannel().sendMessage("Tails!");
            }
        }
    }
}
