package nofortnite.utils.listeners;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import nofortnite.Bot;

public class MessageReceivedListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(!event.getAuthor().isBot() && event.getMessage().getContentRaw().toLowerCase().contains(Bot.getUnholyThingToBan().toLowerCase())) {
            event.getTextChannel().sendMessage("Stop talking about "  + Bot.getUnholyThingToBan() + ". If you ever dare to play that, I'm going to ban you :rage:").queue();
        }
    }
}
