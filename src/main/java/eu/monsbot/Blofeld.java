package eu.monsbot;

import eu.monsbot.listeners.PingListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Blofeld {

    private static Blofeld instance;

    public static MessageManager MESSAGE_MANAGER;
    public static int CODE;

    public static void main(String[] args) {
        Random random = new Random();
        CODE = random.nextInt(10000);
        System.out.println(STR."Code: \{CODE}");
        try {
            instance = new Blofeld();

            MESSAGE_MANAGER = new MessageManager();
        } catch (LoginException _) {
            System.out.println("Login failed, bot token invalid");
        }
    }

    public static Blofeld getInstance() {
        return instance;
    }

    private final ShardManager shardManager;

    public Blofeld() throws LoginException {
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(Secret.TOKEN);
        builder.setStatus(OnlineStatus.IDLE);
        builder.setActivity(Activity.watching("cats :3"));
        addListeners(builder);
        shardManager = builder.build();
    }

    private void addListeners(DefaultShardManagerBuilder builder) {
        builder.addEventListeners(new PingListener());
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public void stopBot() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                // Perform cleanup tasks
                // For example: close JDA connection, shutdown executor service, etc.
                // Replace these with your actual shutdown tasks
                System.out.println("Bot is stopping...");
                Thread.sleep(2000); // Simulating some shutdown tasks

                // Shutdown JDA and exit the application
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}