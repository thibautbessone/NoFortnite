package nofortnite.utils.listeners;

import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.user.update.UserUpdateGameEvent;
import net.dv8tion.jda.core.exceptions.HierarchyException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import nofortnite.Bot;

public class PresenceListener extends ListenerAdapter {

    @Override
    public void onUserUpdateGame(UserUpdateGameEvent event) {
        User scum = event.getUser(); // baddie
        if(event.getNewGame() != null && event.getNewGame().toString().contains(Bot.getUnholyThingToBan())) {
            try {
                event.getJDA().getUserById(scum.getId()).openPrivateChannel().queue((channel) -> {
                    channel.sendMessage("You've been banned from **" + event.getGuild().getName() + "** cause no one wants kids playing " + Bot.getUnholyThingToBan()).complete();
                    event.getGuild().getController().ban(scum, 7, Bot.getUnholyThingToBan() + " kiddie").queue();
                    event.getGuild().getDefaultChannel().sendMessage("I banned " + scum.getAsMention() + " this kid was playing " + Bot.getUnholyThingToBan()).queue();
                    System.out.println("User " + scum.getName() + "#" + scum.getDiscriminator() + " with ID " + scum.getId() + " banned from " + event.getGuild().getName());
                });
            } catch (HierarchyException e) {
                event.getGuild().getDefaultChannel().sendMessage("I can't ban " + scum.getAsMention() + ", this kid is more powerful than me here !").queue();
            }
        }
    }
}
