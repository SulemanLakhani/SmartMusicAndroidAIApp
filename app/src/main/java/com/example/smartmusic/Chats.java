package com.example.smartmusic;

/**
 * Chats.java
 * @author Suleman, Austin, Patrick
 * This java class file contains the getter, setter methods and the constructor for the sender and
 * message
 * date: 04/28/22
 */
public class Chats {

    /** string to store our message and sender */
    private String message;
    private String sender;

    /** getter methods.
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * this method is a setter
     * @param message setter for the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * this is a getter method for the sender
     * @return sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * this is a setter method for the sender
     * @param sender the sender is user or bot
     */
    public void setSender(String sender) {
        this.sender = sender;
    }


    /**
     * this is a constructor for the message and sender
     * @param message contains the response or the user text
     * @param sender the user or the bot
     */
    public Chats(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }

}
