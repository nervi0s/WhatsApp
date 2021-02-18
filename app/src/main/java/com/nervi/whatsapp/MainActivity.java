package com.nervi.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

        chatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Chat chat = chats.get(i);
                chat.setViewed();
                chatsAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemCamera) {
            Log.i("MainActivity", "Elegido Item para abrir la cámara");
            openCamera();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            Log.i("MainActivity", "Lanzando actividad de la cámara");
            startActivityForResult(cameraIntent, 1);
        }
    }

    public void generateRandomChat(View view) {
        DataProvider provider = new DataProvider(this);
        provider.getChatData(chats, chatsAdapter);
    }

}