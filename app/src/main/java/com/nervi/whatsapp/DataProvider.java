package com.nervi.whatsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
               /* try {
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
                    randomTense[0] = rawData.substring(first, last);

                    if (randomTense[0].length() > 40) {
                        randomTense[0] = randomTense[0].substring(0, 41) + "... ";
                    }

                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            chats.add(new Chat("Somos programadores ", randomTense[0], bitmapImage[0]));
                            chatsAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        });

        hiloEvitarProblemasConLlamadaURLEnMain.start();

      /*  try {
            hiloEvitarProblemasConLlamadaURLEnMain.join(); //Esperamos a que el hilo termine y nos de un Bitmap válido
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //return bitmapImage[0];
    }

    public String getRandomImageURL() {
        //final JsonReader[] jsonReader = new JsonReader[1];
        final String test = "hola";
        Thread threadToUrlImage = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStreamReader isr = new InputStreamReader(new URL(apiImgUrl).openStream(), "UTF-8");
                    //jsonReader[0] = new JsonReader(isr);
                    BufferedReader br = new BufferedReader(isr);
                    String jsonString = br.readLine();
                    System.out.println(jsonString);
                    JSONArray jsonArray = new JSONArray(jsonString);
                    JSONObject jo = jsonArray.getJSONObject(0);
                    System.out.println(jsonArray.length());
                    System.out.println(jo.getString("url"));
                    /*
                    System.out.println(jsonReader[0]);
                    System.out.println(jsonReader[0].peek() + " 1");
                    jsonReader[0].beginArray();
                    System.out.println(jsonReader[0].peek() + " 2");
                    //jsonReader[0].beginObject();
                    //System.out.println(jsonReader[0].peek() + " 3");
                    while (jsonReader[0].hasNext()){
                            test(jsonReader[0]);
                    }
                    jsonReader[0].endArray();*/
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        threadToUrlImage.start();
        //ToDo
        return test;
    }

    public String getRandomTitle() {
        //ToDo
        return "";
    }

    /*public void test(JsonReader reader) throws IOException {
        reader.beginObject();
        String test = null;
        System.out.println(reader.peek() + " 3");
        while(reader.hasNext()){
            System.out.println(reader.peek() + " 4 ----");
            String key = reader.nextName();
            if (key.equals("breeds")){
                reader.beginArray();
                while (reader.hasNext()){
                    reader.skipValue();
                }
                reader.endArray();
            }
            else if (key.equals("id")){
                test =reader.nextString();
            }
            else if (key.equals("url")){
                test =reader.nextString();
            }
            else if (key.equals("width")){
                test =reader.nextString();
            }
            else if (key.equals("height")){
                test =reader.nextString();
            }
        }
        reader.endObject();
        System.out.println(test +" 5");
    }*/


}
