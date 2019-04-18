package de.biosphere.log.listener;

import de.biosphere.log.DiscordUtils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.role.RoleCreateEvent;
import net.dv8tion.jda.core.events.role.RoleDeleteEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class GuildListener extends ListenerAdapter {

    @Override
    public void onRoleCreate(RoleCreateEvent event) {
        final EmbedBuilder embedBuilder = DiscordUtils.getLogEmbed();
        embedBuilder.setDescription(String.format("Die Rolle %s wurde erstellt", event.getRole().getName()));
        DiscordUtils.sendEmbedToLog(event.getGuild(), embedBuilder);
    }

    @Override
    public void onRoleDelete(RoleDeleteEvent event) {
        final EmbedBuilder embedBuilder = DiscordUtils.getLogEmbed();
        embedBuilder.setDescription(String.format("Die Rolle %s wurde gelöscht", event.getRole().getName()));
        DiscordUtils.sendEmbedToLog(event.getGuild(), embedBuilder);
    }
}
