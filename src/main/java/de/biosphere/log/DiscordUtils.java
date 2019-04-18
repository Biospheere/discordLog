package de.biosphere.log;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;

import java.awt.*;

public class DiscordUtils {

    private static final EmbedBuilder LOG_EMBED;


    static {
        LOG_EMBED = new EmbedBuilder().setColor(Color.RED);
    }


    public static void sendEmbedToLog(final Guild guild, final EmbedBuilder embedBuilder){
        guild.getTextChannelsByName("log", true).forEach(channel -> channel.sendMessage(embedBuilder.build()).queue());
    }

    public static EmbedBuilder getLogEmbed(){
        return LOG_EMBED;
    }
}
