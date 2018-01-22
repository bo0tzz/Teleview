package me.bo0tzz.teleview.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import me.bo0tzz.teleview.json.TelegramMessageDeserializer;

@JsonDeserialize(using = TelegramMessageDeserializer.class)
public class TelegramMessage {

    private final String id;
    private final boolean valid;

    public TelegramMessage(String id, boolean valid) {
        this.id = id;
        this.valid = valid;
    }

    public String getId() {
        return id;
    }

    public boolean isValid() {
        return valid;
    }

}
