package eu.monsbot.listeners;

import eu.monsbot.Blofeld;
import net.dv8tion.jda.api.entities.ISnowflake;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class PingListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }

        // Check if the bot was mentioned
        Message message = event.getMessage();
        List<String> mentions = message.getMentions().getUsers().stream()
                .map(ISnowflake::getId)
                .toList();

        String botId = event.getJDA().getSelfUser().getId();
        if (mentions.contains(botId)) {

            String messageStr = event.getMessage().getContentRaw();
            // mentions structured like "<@" + botId + ">", so 3 for the '<>''@' and '>'
            int mentionLength = botId.length()+3;
            messageStr = messageStr.substring(mentionLength).trim();

            Blofeld.MESSAGE_MANAGER.processMessage(messageStr, event);
        }
    }
}
