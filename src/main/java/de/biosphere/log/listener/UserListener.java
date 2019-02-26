package de.biosphere.log.listener;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.guild.GuildBanEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

/**
 * @author Biosphere
 * @date 09.04.18
 */
public class UserListener extends ListenerAdapter {

    @Override
    public void onGuildBan(GuildBanEvent event) {
        if(event.getUser() == null){
            return;
        }
        final EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.RED);
        embedBuilder.setDescription(event.getUser().getName() + "#" + event.getUser().getDiscriminator() + " wurde gebannt");
        event.getGuild().getTextChannelsByName("log", true).forEach(channel -> channel.sendMessage(embedBuilder.build()).queue());
    }

}
