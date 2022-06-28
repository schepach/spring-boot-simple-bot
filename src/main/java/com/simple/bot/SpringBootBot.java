package com.simple.bot;

import com.simple.model.general.IGeneral;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SpringBootBot extends TelegramLongPollingBot {

    private final String botName;
    private final String botToken;
    private final Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Autowired
    private IGeneral general;

    public SpringBootBot() {
        botName = "";
        botToken = "";
    }

    @Override
    public void onUpdateReceived(Update update) {

        try {
            if (update != null) {

                Message message = update.getMessage();

                if (update.hasMessage() && message.hasText()) {

                    String text = message.getText();
                    String userId = String.valueOf(message.getFrom().getId());

                    if (text.equals("/start")) {
                        this.general.sayHello(userId);
                    } else if (text.equals("/ping")) {
                        this.general.ping(userId);
                    }
                }

                // Ловим callback
                if (update.hasCallbackQuery()) {
                    String callBackData = update.getCallbackQuery().getData();
                    String callBackQueryId = update.getCallbackQuery().getId();
                    Message callbackMessage = update.getCallbackQuery().getMessage();
                    int messageId = callbackMessage.getMessageId();
                    String userId = String.valueOf(update.getCallbackQuery().getFrom().getId());
                    logger.log(Level.SEVERE, "callBackData - {0}", callBackData);
                    logger.log(Level.SEVERE, "userId - {0}", userId);
                }
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}