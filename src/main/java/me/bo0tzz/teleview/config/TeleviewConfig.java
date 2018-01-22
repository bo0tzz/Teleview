package me.bo0tzz.teleview.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("me.bo0tzz.teleview")
public class TeleviewConfig {

    /**
     * This is a bit dodgy when running through gradle - the env var doesn't get passed properly.
     * You can figure it out :)
     *
     * @return the API key to powertelegram
     */
    @Bean
    public String powertgKey() {
        return System.getenv("PWRTG_KEY");
    }

}
