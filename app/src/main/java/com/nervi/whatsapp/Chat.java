package com.nervi.whatsapp;

public class Chat {
    private final String title;
    private final String body;
    private final String imgUrl;

    public Chat(String title, String body, String imgUrl) {
        this.title = title;
        this.body = body;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
