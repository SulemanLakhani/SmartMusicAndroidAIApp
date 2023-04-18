package com.example.smartmusic;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * DisplayMessageActivity.java
 * @author Suleman, Austin, Patrick
 * This java class file contains the implementation of the display message activity.
 * Date: 04-28-22
 */
public class DisplayMessageActivity extends AppCompatActivity {

    private RecyclerView chats;
    private EditText userMsg;
    private FloatingActionButton sendMsg;
    private final String BOT = "bot";
    private final String USER = "user";
    private ArrayList<Chats> chatsArrayList;
    private ChatAdapter chatAdapter;

    static String tempStr;

    @Override
    /**
     * this method displays the chat messages and contains the edit text box and send button
     * contains the implementation for the adapters
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        chats = findViewById(R.id.idChats);
        userMsg = findViewById(R.id.idEdtMessage);
        sendMsg = findViewById(R.id.idSend);
        chatsArrayList = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatsArrayList, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        chats.setLayoutManager(manager);
        chats.setAdapter(chatAdapter);

        //click listener for send Button
        sendMsg.setOnClickListener(new View.OnClickListener() {

            @Override
            /**
             * this method sends a Toast message that says
             * "Please enter your message" to the user if the user sends an empty message,
             * else if the user types a message it returns the response from the API for that
             * particular message
             */
            public void onClick(View view) {
                String msg = userMsg.getText().toString();
                System.out.println("[user input = "+ msg + "]");
                if(userMsg.getText().toString().isEmpty()) {
                    Toast.makeText(DisplayMessageActivity.this, "Please enter your message", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    getResponse(userMsg.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                userMsg.setText("");
            }
        });

    }

    /**
     * @param message user input in the text box
     * @throws IOException
     * the response from the BrainShop API for a particular user message input
     */
    private void getResponse(String message) throws IOException {
        System.out.println("message = " + message);
        chatsArrayList.add(new Chats(message,USER));
        chatAdapter.notifyDataSetChanged();
        for(int i = 0; i< chatsArrayList.size(); i++){
            System.out.println(chatsArrayList);
        }

        String URL = "http://api.brainshop.ai/get?bid=165361&key=qcRNGI9WWxgUcabt&uid=[uid]&msg=" + message;
        System.out.println(URL);

        String BASE_URL = "http://api.brainshop.ai/";

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<MessageModal> call = retrofitAPI.getMessage(URL);
        call.enqueue(new Callback<MessageModal>() {
            @Override
            /**
             * this method will check for the successful response from the API and the response code "200"
             */
            public void onResponse(Call<MessageModal> call, Response<MessageModal> response) {
                int statusCode = response.code();
                if(response.isSuccessful() && response.code() == 200) {
                    System.out.println("[API response code = "+statusCode + ".]" );
                    MessageModal modal = response.body();
                    System.out.println("Line 151 = " + modal.getCnt());
                    tempStr = modal.getCnt().toString();
                    System.out.println("Line 152 = " + response.body().toString());
                    String res = modal.getCnt();
                    System.out.println("Line 154 = " + res);
                    chatsArrayList.add(new Chats(res, BOT));
                    chatAdapter.notifyDataSetChanged();
                }
            }

            @Override
            /**
             * This method onFailure sends the response as "please revert your question" if there's
             * no response from the API
             */
            public void onFailure(Call<MessageModal> call, Throwable t) {
                chatsArrayList.add(new Chats("Please revert your question",BOT));
                chatAdapter.notifyDataSetChanged();
            }
        });
    }

}