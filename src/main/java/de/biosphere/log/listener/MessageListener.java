package de.biosphere.log.listener;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageDeleteEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageEditEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;

/**
 * @author Biosphere
 * @date 09.04.18
 */
public class MessageListener {

    @EventSubscriber
    public void onDelete(MessageDeleteEvent event) {
        if(event.getMessage() == null){
            return;
        }
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.withColor(Color.RED);
        embedBuilder.withTitle("Nachricht gelöscht  in #" + event.getChannel().getName());
        embedBuilder.withFooterText("@" + event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator());
        embedBuilder.withFooterIcon(event.getAuthor().getAvatarURL());

        embedBuilder.appendDescription(event.getMessage().toString());

        IChannel channel = event.getGuild().getChannelsByName("logger").stream().findFirst().orElse(null);
        if(channel != null){
            channel.sendMessage(embedBuilder.build());
        }
    }

    @EventSubscriber
    public void onEdit(MessageEditEvent event) {
        if(event.getNewMessage() == null ||event.getOldMessage() == null){
            return;
        }
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.withColor(Color.RED);
        embedBuilder.withTitle("Nachricht editiert in #" + event.getChannel().getName());
        embedBuilder.withFooterText("@" + event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator());
        embedBuilder.withFooterIcon(event.getAuthor().getAvatarURL());

        embedBuilder.appendDescription(event.getOldMessage().getFormattedContent() + "\n—\n" + event.getNewMessage());

        IChannel channel = event.getGuild().getChannelsByName("logger").stream().findFirst().orElse(null);
        if(channel != null){
            channel.sendMessage(embedBuilder.build());
        }
    }

}
