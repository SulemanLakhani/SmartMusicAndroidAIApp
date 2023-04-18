package com.example.smartmusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/** ChatAdapter.java
 * @author Suleman, Austin, Patrick
 * This java class file contains the multiple recycler Views
 * and displays the chat messages in a Text View inside the recycler view
 * in the Display Message activity.
 * date: 04-28-22
 */
public class ChatAdapter extends RecyclerView.Adapter {

    private ArrayList<Chats> chatsArrayList;
    private Context context;
    private final String BOT = "bot";
    private final String USER = "user";

    public ChatAdapter(ArrayList<Chats> chatsArrayList, Context context) {
        this.chatsArrayList = chatsArrayList;
        this.context = context;
    }

    @Override
    /**
     * this method creates the recycler view for the chat messages to be displayed
     * below code is to switch our layout type along with view holder.
     */
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg,parent,false);
                return new UserView(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_msg,parent,false);
                return new BotView(view);
        }
        return null;
    }

    @Override
    /**
     * this method is used to set data to our layout file.
     * this method displays the message from either the user or bot.
     */
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        Chats chats = chatsArrayList.get(position);
        switch(chats.getSender()) {
            case USER:
                ((UserView)holder).user.setText(chats.getMessage());
                break;
            case BOT:
                ((BotView)holder).bot.setText(chats.getMessage());
                break;
        }
    }

    @Override
    /**
     * this method determines if it's either the user or bot
     * @return an int - '0' for "user", '1' for "bot", '-1' for "default"
     */
    public int getItemViewType(int position) {
        switch(chatsArrayList.get(position).getSender()) {
            case "user":
                return 0;
            case "bot":
                return 1;
            default:
                return -1;
        }
    }

    @Override
    /**
     * this method returns the size of the arraylist.
     */
    public int getItemCount() {
        return chatsArrayList.size();
    }

    /**
     * this method displays the messages from the user in a text view
     */
    public static class UserView extends RecyclerView.ViewHolder {

        TextView user;

        public UserView(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.idUser);
        }
    }
    /**
     * this method displays the messages from the bot in a text view
     */
    public static class BotView extends RecyclerView.ViewHolder {
        TextView bot;

        public BotView(View itemView) {
            super(itemView);
            bot = itemView.findViewById(R.id.idBot);
            }
        }
}
