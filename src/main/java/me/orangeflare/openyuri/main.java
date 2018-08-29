package me.orangeflare.openyuri;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        configManager config = new configManager();

        String APIToken = config.read("discordAPI");
        if (APIToken.isEmpty()) {
            System.err.println("No API Token Provided!");
            System.exit(0);
        }

        DiscordApi yuri = new DiscordApiBuilder().setToken(APIToken).login().join();

        yuri.addMessageCreateListener(event -> {
            if (event.getMessage().getContent().equalsIgnoreCase("y.about")) {
                new MessageBuilder()
                        .setEmbed(new EmbedBuilder()
                                .setAuthor("Josef Baltz", "http://orangeflare.me/", "https://opensource.org/files/osi_keyhole_300X300_90ppi_0.png")
                                .setThumbnail("https://vignette.wikia.nocookie.net/doki-doki-literature-club/images/a/a1/Yuri_sticker_1.png/revision/latest?cb=20171112094412")
                                .addField("OpenYuri Developer", "OrangeFlare#1337", true)
                                .addField("YuriTheKnifeWaifu Developer", "The Greatest Hero#0001", true)
                                .addField("GitHub", "https://github.com/OrangeFlare/OpenYuriBot", false)
                                .setTitle("OpenYuri v1.0.0")
                                .setDescription("About Me!")
                                .setFooter("OpenYuri"))
                        .send(event.getChannel());
            }
        });
    }
}
