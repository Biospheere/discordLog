package de.biosphere.log;

import de.biosphere.log.listener.MessageListener;
import de.biosphere.log.listener.UserListener;
import net.dv8tion.jda.api.JDABuilder;

/**
 * @author Biosphere
 * @date 09.04.18
 */
public class DiscordLog {

    private DiscordLog() throws Exception {
        new JDABuilder()
                .setToken(System.getenv("DISCORD_TOKEN"))
                .addEventListeners(new MessageListener(), new UserListener())
                .build();
    }

    public static void main(String[] args) throws Exception {
        new DiscordLog();
    }
}
