package com.example.smartmusic;


import com.google.gson.annotations.SerializedName;

/**
 * MessageModal.java
 * @author Suleman, Austin, Patrick
 * This java class file stores the message response from the API
 * date: 04-28-22
 */
public class MessageModal {

    @SerializedName("cnt")
    private String cnt;



    /**
     * getter methods.
     * @return cnt message response from the API
     */
    public String getCnt() {
        return cnt;
    }

}