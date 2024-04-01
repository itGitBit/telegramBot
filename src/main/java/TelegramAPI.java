import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;


public class TelegramAPI extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                String message = update.getMessage().getText();
                long chatId = update.getMessage().getChatId();
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText(message);
                execute(sendMessage);
            }
        } catch (TelegramApiException e) {
            sendAdminExceptions(e);
        }

    }


    @Override
    public String getBotUsername() {
        return "My-bot-name";
    }

    @Override
    public String getBotToken() {
        return "My-api-token";
    }



    private void sendAdminExceptions(Exception e) {
        try {
            SendMessage sendMessage = new SendMessage();
            //todo add admin chat id
            long chatId = 0;
            sendMessage.setChatId(chatId);
            sendMessage.setText(e.toString() + Arrays.toString(e.getStackTrace()));
            execute(sendMessage);
        } catch (Exception d) {
            e.printStackTrace();
        }
    }
}

