package eu.monsbot;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class Blofeld {

    public static void main(String[] args) {
        try {
            Blofeld blofeld = new Blofeld();
        } catch (LoginException _) {
            System.out.println("Login failed, bot token invalid");
        }
    }

    private final ShardManager shardManager;

    public Blofeld() throws LoginException {
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(Secret.TOKEN);
        builder.setStatus(OnlineStatus.IDLE);
        builder.setActivity(Activity.watching("cats :3"));
        shardManager = builder.build();
    }

    public ShardManager getShardManager() {
        return shardManager;
    }
}