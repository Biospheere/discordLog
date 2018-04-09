package de.biosphere.log;

import de.biosphere.log.listener.MessageListener;
import de.biosphere.log.listener.UserListener;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;

/**
 * @author Biosphere
 * @date 09.04.18
 */
public class DiscordLog {

    private DiscordLog() {
        IDiscordClient client = new ClientBuilder().withToken(System.getenv("DISCORD-TOKEN")).login();
        client.getDispatcher().registerListener((IListener<ReadyEvent>) event -> {
            event.getClient().getDispatcher().registerListener(new MessageListener());
            event.getClient().getDispatcher().registerListener(new UserListener());
        });
    }

    public static void main(String... args) {
        new DiscordLog();
    }

}
