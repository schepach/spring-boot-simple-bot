package com.simple.model.general;

import com.simple.bot.SpringBootBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class General implements IGeneral {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void sayHello(String userId) {
        try {
            applicationContext.getBean("springBootBot", SpringBootBot.class).execute(SendMessage.builder()
                    .chatId(userId)
                    .text("Привет!🤖\n/ping")
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void ping(String userId) {
        try {
            applicationContext.getBean("springBootBot", SpringBootBot.class).execute(SendMessage.builder()
                    .chatId(userId)
                    .text("pong🏓")
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
