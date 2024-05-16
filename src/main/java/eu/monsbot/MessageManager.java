package eu.monsbot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageCreateAction;
import net.dv8tion.jda.api.utils.FileUpload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MessageManager {

    public void processMessage(String messageStr, MessageReceivedEvent event) {

        if (messageStr.equals(STR."!stop \{Blofeld.CODE}")) {
            event.getMessage().reply("stopping bot...");
            Blofeld.getInstance().stopBot();
            return;
        }

        sendImage(event.getChannel(),"C:\\Users\\Monsbot\\Pictures\\blofeld\\pics\\cat_goofy.jpg");
    }

    private void sendImage(MessageChannel channel, String imagePath) {
        try {
            // Read image file and convert to File object
            Path path = Paths.get(imagePath);
            byte[] imageData = Files.readAllBytes(path);

            // Send the image as an attachment
            channel.sendFiles(FileUpload.fromData(imageData, "image.png")).queue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
