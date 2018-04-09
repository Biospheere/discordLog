package de.biosphere.log.listener;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.member.NicknameChangedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;

/**
 * @author Biosphere
 * @date 09.04.18
 */
public class UserListener {

    @EventSubscriber
    public void onNicknameChange(NicknameChangedEvent event){
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.withColor(Color.CYAN);
        if(!event.getNewNickname().isPresent()){
            embedBuilder.withTitle("Nickname zurückgesetzt");
            embedBuilder.appendDesc("User: " + event.getUser().getName());
        } else {
            embedBuilder.withTitle("Nickname geändert");
            if(event.getNewNickname().isPresent()){
                embedBuilder.appendDesc("Neuer Nickname: " + event.getNewNickname().get() + "\n");
            }
            if(event.getOldNickname().isPresent()){
                embedBuilder.appendDesc("Alter Nickname: " + event.getOldNickname().get());
            }
        }
        embedBuilder.withFooterText("@" + event.getUser().getName() + "#" + event.getUser().getDiscriminator());
        embedBuilder.withFooterIcon(event.getUser().getAvatarURL());
        IChannel channel = event.getGuild().getChannelsByName("logger").stream().findFirst().orElse(null);
        if(channel != null){
            channel.sendMessage(embedBuilder.build());
        }
    }
}
