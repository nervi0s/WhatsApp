package com.nervi.whatsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class DataProvider {
    public static final String apiImgUrl = "https://api.thecatapi.com/v1/images/search";
    public static final String apiMsgUrl = "https://api.chucknorris.io/jokes/random";
    private URL url;

    public void getUrlImage(ArrayList<Chat> chats, ChatsAdapter chatsAdapter, Handler handler) {

        final String[] randomImageUrl = new String[1];
        final Bitmap[] bitmapImage = new Bitmap[1];
        final String[] bodyMsg = new String[1];

        Thread hiloEvitarProblemasConURLEnMain = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //Obtenemos la los datos desde los que vamos a extraer la URL de la imagen
                    url = new URL(apiImgUrl);
                    InputStream is = url.openStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String rawData = br.readLine(); //Nos deviuelve uns json en formato String
                    int first = rawData.indexOf("\"url\":\"") + 7;
                    int last = rawData.indexOf("\",\"width\"");
                    randomImageUrl[0] = rawData.substring(first, last); //Extraemos de ese String la URL que nos interesa

                    //Transfromar esa URL de imagen a un Bitmap
                    url = new URL(randomImageUrl[0]);
                    bitmapImage[0] = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                    ////Obtenemos la los datos desde los que vamos a extraer el cuerpo del mensaje
                    url = new URL(apiMsgUrl);
                    is = url.openStream();
                    br = new BufferedReader(new InputStreamReader(is));
                    rawData = br.readLine();
                    System.out.println(rawData);
                    first = rawData.indexOf("\"value\":\"") + 9;
                    last = rawData.indexOf("\"}");
                    bodyMsg[0] = rawData.substring(first, last);

                    if (bodyMsg[0].length() > 40) {
                        bodyMsg[0] = bodyMsg[0].substring(0, 41) + "... ";
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            chats.add(new Chat("Título", bodyMsg[0], bitmapImage[0]));
                            chatsAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        hiloEvitarProblemasConURLEnMain.start();

      /*  try {
            hiloEvitarProblemasConURLEnMain.join(); //Esperamos a que el hilo termine y nos de un Bitmap válido
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //return bitmapImage[0];
    }

    public String getRandomTense() {
        //ToDo
        return "";
    }

    public RoundedBitmapDrawable getRoundedImage (Bitmap bm) {
        Bitmap copiaBitmap = bm.copy(bm.getConfig(),true);
        copiaBitmap.setHeight(100);
        copiaBitmap.setWidth(100);
        RoundedBitmapDrawable rbd = RoundedBitmapDrawableFactory.create(null, copiaBitmap);

        rbd.setCornerRadius(copiaBitmap.getHeight());

        return rbd;
    }

}
