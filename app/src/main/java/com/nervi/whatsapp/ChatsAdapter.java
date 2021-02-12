package com.nervi.whatsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatsAdapter extends BaseAdapter {
    private final Context mContext;
    private final ArrayList<Chat> chats;

    public ChatsAdapter(Context c, ArrayList<Chat> chats) {
        this.mContext = c;
        this.chats = chats;
    }

    @Override
    public int getCount() {
        return chats.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        System.out.println("asdfgdkjasdhkjasdhjkashdjkashdkjashdjkashdkjashdkjsh");
        Chat chat = chats.get(i);

        if (view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.message_layout, null);
        }

        final ImageView chatImage = view.findViewById(R.id.imagenChat);
        final TextView chatTitle = view.findViewById(R.id.tituloMensaje);
        final TextView chatBody = view.findViewById(R.id.cuerpoMensaje);

        chatImage.setImageBitmap(chat.getImg());
        chatTitle.setText(chat.getTitle());
        chatBody.setText(chat.getBody());

        return view;
    }
}
