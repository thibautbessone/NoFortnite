package nofortnite;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import nofortnite.utils.listeners.MessageReceivedListener;
import nofortnite.utils.listeners.PresenceListener;

import javax.security.auth.login.LoginException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @file Bot.java
 * @author Blue
 * @version It's a first
 * @brief Bot main class
 */

// Invite https://discordapp.com/oauth2/authorize?client_id=531857335690330133&scope=bot&permissions=67111942

public class Bot {

    private static String unholyThingToBan;

    public static String getUnholyThingToBan() {
        return unholyThingToBan;
    }

    public static void main(String[] args) {
        JsonObject config;
        JsonParser parser = new JsonParser();
        try {
            config = parser.parse(new FileReader("config.json")).getAsJsonObject();
            try {
                unholyThingToBan = config.get("unholyThingToBan").getAsString();
                JDA builder = new JDABuilder(AccountType.BOT).setToken(config.get("token").getAsString())
                        .addEventListener(new MessageReceivedListener())
                        .addEventListener(new PresenceListener()).setBulkDeleteSplittingEnabled(false).buildBlocking();
                builder.getPresence().setGame(Game.playing(config.get("activity").getAsString()));
                System.out.println("Bot started, watching for " + unholyThingToBan);
            } catch (LoginException | InterruptedException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Missing or corrupted file : config.json");
        }
    }
}