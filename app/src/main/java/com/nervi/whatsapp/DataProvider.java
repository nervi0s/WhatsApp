package com.nervi.whatsapp;

import android.app.Activity;
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
    private Activity activity;

    public DataProvider(Activity activity) {
        this.activity = activity;
    }

    public void getChatData(ArrayList<Chat> chats, ChatsAdapter chatsAdapter) {


        new Thread(new Runnable() { // Hilo anónimo encargado de delegar 2 tareas a otros dos hilos
            String imgUrl;
            String tense;
            Bitmap bitmapImage;

            @Override
            public void run() {
                Thread threadImage = new Thread(new Runnable() { // Hilo encargado de obtener una URL aleatroria de una imagen
                    @Override
                    public void run() {
                        imgUrl = UtilsThread.getRandomImageURL();
                    }
                });

                Thread threadTense = new Thread(new Runnable() { // Hilo encargado de obtener una frase aleatoria
                    @Override
                    public void run() {
                        tense = UtilsThread.getRandomTense();
                    }
                });

                threadImage.start();
                threadTense.start();

                // Hacemos que el hilo anónimo espera a que acaben las tareas los 2 hilos a los que llama
                try {
                    threadImage.join();
                    threadTense.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Convertimos la imagen proporcionada desde una URL a un Bitmap
                try {
                    bitmapImage = BitmapFactory.decodeStream(new URL(imgUrl).openStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Usamos la actividad pasada como argumento para poder usar el método runOnUiThread() para efectuar cambios en el hilo de la UI
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        chats.add(new Chat("SOMOS ", tense, bitmapImage));
                        chatsAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();

    }
}
