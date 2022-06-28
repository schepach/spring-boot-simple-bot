package com.simple.app;

import com.simple.bot.SpringBootBot;
import com.simple.model.general.General;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public General general() {
        return new General();
    }

    @Bean
    public SpringBootBot springBootBot() {
        return new SpringBootBot();
    }
}
