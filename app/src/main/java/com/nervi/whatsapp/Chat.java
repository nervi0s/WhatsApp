package com.nervi.whatsapp;

import android.graphics.Bitmap;

import java.util.Calendar;
import java.util.Date;

public class Chat {
    public static int chatsCounter = 0;

    private final Date date;
    private final String title;
    private final String body;
    private final Bitmap img;
    private boolean viewed = false;

    public Chat(String title, String body, Bitmap img) {
        chatsCounter++;
        this.title = title + chatsCounter;
        this.body = body;
        this.img = img;
        date = new Date();
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

    public String getDate() {
        Calendar timeNow = Calendar.getInstance();
        timeNow.setTime(date);
        return timeNow.get(Calendar.HOUR_OF_DAY) + ":" + timeNow.get(Calendar.MINUTE);
    }

    public void setViewed() {
        this.viewed = true;
    }

    public boolean isViewed() {
        return viewed;
    }
}
