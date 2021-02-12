package com.nervi.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView chatList;
    private final ArrayList<Chat> chats = new ArrayList<>();
    private ChatsAdapter chatsAdapter;
    protected Handler mainHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatList = findViewById(R.id.listaChats);

        chatsAdapter = new ChatsAdapter(this, chats);
        chatList.setAdapter(chatsAdapter);


    }

    public void generateRandomChat(View view) {

        DataProvider provider = new DataProvider();
        provider.getUrlImage(chats, chatsAdapter, mainHandler);
        //chats.add(new Chat("Título", "Cuerpo", provider.getUrlImage()));
        //chats.add(new Chat("Título", "Cuerpo", provider.getUrlImage()));

        //chatsAdapter.notifyDataSetChanged();

    }

}