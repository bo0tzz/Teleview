package me.bo0tzz.teleview.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import me.bo0tzz.teleview.bean.TelegramMessage;

import java.io.IOException;

public class TelegramMessageDeserializer extends StdDeserializer<TelegramMessage> {

    public TelegramMessageDeserializer() {
        this(null);
    }

    public TelegramMessageDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public TelegramMessage deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {

        JsonNode node = jp.getCodec().readTree(jp);
        JsonNode dialog = node.get("result").get("dialogs").get(0);

        String id = dialog.get("top_message").asText();

        TelegramMessage msg = new TelegramMessage(id, !id.equalsIgnoreCase("0"));
        return msg;
    }
}
