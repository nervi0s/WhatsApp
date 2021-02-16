package com.nervi.whatsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class DataProvider {
    public static final String apiImgUrl = "https://api.thecatapi.com/v1/images/search";
    public static final String apiMsgUrl = "https://api.chucknorris.io/jokes/random";

    public void getChatData(ArrayList<Chat> chats, ChatsAdapter chatsAdapter, Handler mainHandler) {

        final String[] randomImageUrl = new String[1];
        final Bitmap[] bitmapImage = new Bitmap[1];
        final String[] randomTense = new String[1];

        Thread hiloEvitarProblemasConLlamadaURLEnMain = new Thread(new Runnable() {
            @Override
            public void run() {

                UtilsThread threadImage = new UtilsThread("imagen");
                UtilsThread threadTense = new UtilsThread("frase");

                threadImage.start();
                threadTense.start();

                try {
                    threadImage.join();
                    threadTense.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                randomImageUrl[0] = threadImage.getResult();
                randomTense[0] = threadTense.getResult();

                Log.i("DataProvider", "URL de la imagen obtenida -> " + randomImageUrl[0]);
                Log.i("DataProvider", "Frase obtenida -> " + randomTense[0]);

                //Transformar la URL de la imagen en un Bitmap
                try {
                    bitmapImage[0] = BitmapFactory.decodeStream(new URL(randomImageUrl[0]).openConnection().getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        chats.add(new Chat("Somos programadores ", randomTense[0], bitmapImage[0]));
                        chatsAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        hiloEvitarProblemasConLlamadaURLEnMain.start();
    }
}
