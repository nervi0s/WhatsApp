package com.nervi.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView chatList;
    private ArrayList<Chat> chats = new ArrayList<>();

    //private Chat[] chats = {new Chat("Título", "Cuerpo", "imgUrl")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatList = findViewById(R.id.listaChats);
        generate10RandomsChats(null);
        chatList.setAdapter(new ChatsAdapter(this, chats));
    }

    public void generate10RandomsChats(View view) {
        chats.add(new Chat("Título", "Cuerpo", "imgUrl"));
        chats.add(new Chat("Título", "Cuerpo", "imgUrl"));
        chats.add(new Chat("Título", "Cuerpo", "imgUrl"));
        chats.add(new Chat("Título", "Cuerpo", "imgUrl"));
        chats.add(new Chat("Título", "Cuerpo", "imgUrl"));
        chats.add(new Chat("Título", "Cuerpo", "imgUrl"));
        chats.add(new Chat("Título", "Cuerpo", "imgUrl"));
        chats.add(new Chat("Título", "Cuerpo", "imgUrl"));
        chats.add(new Chat("Título", "Cuerpo", "imgUrl"));
        chats.add(new Chat("Título", "Cuerpo", "imgUrl"));
        chats.add(new Chat("Título", "Cuerpo", "imgUrl"));
        chats.add(new Chat("Título", "Cuerpo", "imgUrl"));
        chats.add(new Chat("Título", "Cuerpo", "imgUrl"));
        chats.add(new Chat("Título", "Cuerpo", "imgUrl"));
        chats.add(new Chat("Título", "Cuerpo", "imgUrl"));
        chats.add(new Chat("Título", "Cuerpo", "imgUrl"));
        //TODO
    }

}