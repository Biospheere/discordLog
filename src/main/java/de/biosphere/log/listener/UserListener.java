package de.biosphere.log.listener;

import de.biosphere.log.DiscordUtils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.guild.GuildBanEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

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
        final EmbedBuilder embedBuilder = DiscordUtils.getLogEmbed();
        embedBuilder.setDescription(event.getUser().getName() + "#" + event.getUser().getDiscriminator() + " wurde gebannt");
        event.getGuild().getTextChannelsByName("log", true).forEach(channel -> channel.sendMessage(embedBuilder.build()).queue());
    }

}
