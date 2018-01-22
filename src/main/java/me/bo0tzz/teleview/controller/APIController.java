package me.bo0tzz.teleview.controller;

import me.bo0tzz.teleview.bean.TelegramMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/api")
public class APIController {

    private final String POWERTG_URL_TEMPLATE = "https://api.bo0tzz.me/%s/messages.getPeerDialogs?";
    private final String POWERTG_KEY;
    private final String POWERTG_URL;

    private final String QUERY_TEMPLATE = "peers=%%5B%%22%%40%s%%22%%5D";

    private final String EMBED_URL_TEMPLATE = "https://t.me/%s/%s?embed=1";

    private final String MSG_NOT_SUPPORTED = "<div class=\"message_media_not_supported\">";
    private final String MSG_ERROR = "<div class=\"tgme_widget_message_error\"";

    @Autowired
    public APIController(String powertgKey) {

        this.POWERTG_KEY = powertgKey;
        POWERTG_URL = String.format(POWERTG_URL_TEMPLATE, POWERTG_KEY);

    }

    @RequestMapping("/validmessage/{channel}/{messageid}")
    public TelegramMessage validMessage(@PathVariable String channel, @PathVariable String messageid) {

        String url = String.format(EMBED_URL_TEMPLATE, channel, messageid);
        URI uri = URI.create(url);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {

            String html = response.getBody();

            if (html.contains(MSG_NOT_SUPPORTED) || html.contains(MSG_ERROR)) {
                return new TelegramMessage(messageid, false);
            }

        }

        //If we're not sure whether the message is valid, just render it
        return new TelegramMessage(messageid, true);

    }

    @RequestMapping("/topMessage")
    public Future<TelegramMessage> topMessage(@RequestParam(name = "channel") String channel) {

        return CompletableFuture.supplyAsync(
                () -> {
                    try {
                        String query = String.format(QUERY_TEMPLATE, channel);
                        System.out.println(query);
                        URI uri = URI.create(POWERTG_URL + query);
                        System.out.println(uri);
                        return new RestTemplate().getForObject(uri, TelegramMessage.class);
                    } catch (RestClientException ex) {
                        ex.printStackTrace();
                    }

                    return null;
                });

    }

}
