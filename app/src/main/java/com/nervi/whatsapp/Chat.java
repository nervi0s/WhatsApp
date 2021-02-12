package com.nervi.whatsapp;

import android.graphics.Bitmap;

public class Chat {
    private final String title;
    private final String body;
    private final Bitmap img;

    public Chat(String title, String body, Bitmap img) {
        this.title = title;
        this.body = body;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Bitmap getImg() {
        return img;
    }
}
