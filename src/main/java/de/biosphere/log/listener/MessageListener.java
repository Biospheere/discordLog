package de.biosphere.log.listener;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.jodah.expiringmap.ExpiringMap;

import java.awt.*;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Biosphere
 * @date 09.04.18
 */
public class MessageListener extends ListenerAdapter {

    private final Map<String, Message> messageCache;

    public MessageListener(){
        final ExpiringMap.Builder<Object, Object> mapBuilder = ExpiringMap.builder();
        mapBuilder.maxSize(1000).expiration(5, TimeUnit.MINUTES).build();
        messageCache = mapBuilder.build();
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if(event.getAuthor().isBot()){
            return;
        }
        messageCache.put(event.getMessageId(), event.getMessage());
    }

    @Override
    public void onGuildMessageDelete(GuildMessageDeleteEvent event) {
        if(!messageCache.containsKey(event.getMessageId())){
            return;
        }
        final Message message = messageCache.get(event.getMessageId());
        final EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.RED);
        embedBuilder.setTitle("Nachricht gelöscht  in #" + event.getChannel().getName());
        embedBuilder.setFooter("@" + message.getAuthor().getName() + "#" + message.getAuthor().getDiscriminator(), message.getAuthor().getAvatarUrl());
        embedBuilder.setDescription(message.getContentRaw() + "\n");

        event.getGuild().getTextChannelsByName("logger", true).forEach(channel -> channel.sendMessage(embedBuilder.build()).queue());
    }

    @Override
    public void onGuildMessageUpdate(GuildMessageUpdateEvent event) {
        if(event.getAuthor().isBot()){
            return;
        }
        if(!messageCache.containsKey(event.getMessageId())){
            return;
        }
        final Message message = messageCache.get(event.getMessageId());
        final EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.RED);
        embedBuilder.setTitle("Nachricht editiert in #" + event.getChannel().getName());
        embedBuilder.setFooter("@" + event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator(), event.getAuthor().getAvatarUrl());
        embedBuilder.setDescription(message.getContentRaw() + "\n—\n" + event.getMessage().getContentRaw());

        event.getGuild().getTextChannelsByName("logger", true).forEach(channel -> channel.sendMessage(embedBuilder.build()).queue());
    }


}
