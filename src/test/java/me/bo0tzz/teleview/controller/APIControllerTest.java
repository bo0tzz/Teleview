package me.bo0tzz.teleview.controller;

import me.bo0tzz.teleview.bean.TelegramMessage;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class APIControllerTest {

    @Test
    public void topMessage() throws UnsupportedEncodingException, ExecutionException, InterruptedException {

        APIController c = new APIController("user369811608:JsK9_blIUEZDQba-l4zMsZGLJ2G705VJaAJxrx82sZQ");
        TelegramMessage m = c.topMessage("iranprotestsenglish").get();

        System.out.println(m.getId());
        System.out.println(m.isValid());

    }
}