package com.nervi.whatsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UtilsThread extends Thread {
    final String methodName;
    String result;

    public UtilsThread(String methodName) {
        this.methodName = methodName;
    }


    @Override
    public void run() {
        if (methodName.equalsIgnoreCase("imagen")) {
            result = getRandomImageURL();
        } else if (methodName.equalsIgnoreCase("frase")) {
            result = getRandomTense();
        }
    }

    public String getRandomImageURL() {
        String randomURL = null;

        try {
            InputStreamReader isr = new InputStreamReader(new URL(DataProvider.apiImgUrl).openStream(), "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String jsonString = br.readLine();
            Log.i("UtilsThreads_getImg", "JSON obtenido -> " + jsonString);
            JSONArray jsonArray = new JSONArray(jsonString);
            Log.i("UtilsThreads_getImg", "Length del JSONArray -> " + jsonArray.length());
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            randomURL = jsonObject.getString("url");
            Log.i("UtilsThreads_getImg", "URL de la imagen del JSON obtenido -> " + randomURL);

            br.close();
            isr.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return randomURL;
    }

    public String getRandomTense() {
        String randomTense = null;

        try {
            InputStreamReader isr = new InputStreamReader(new URL(DataProvider.apiMsgUrl).openStream());
            BufferedReader br = new BufferedReader(isr);

            String jsonString = br.readLine();
            Log.i("UtilsThreads_getTense", "JSON obtenido -> " + jsonString);
            JSONObject jsonObject = new JSONObject(jsonString);
            Log.i("UtilsThreads_getTense", "Length del JSONObject -> " + jsonObject.length());
            randomTense = jsonObject.getString("value");
            Log.i("UtilsThreads_getTense", "Frase del JSON obtenida -> " + randomTense);

            br.close();
            isr.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return randomTense;
    }
}
