package com.example.sidkathuria14.dishtv.models;

import java.util.ArrayList;

/**
 * Created by sidkathuria14 on 23/6/18.
 */

public class main {
    ArrayList<String> happy_buffer,sad_buffer,fear_buffer,love_buffer,angry_buffer,top_keywords;
    int total_happy,total_sad,total_fear,total_love,total_angry;
    ArrayList<String> angry_phrases,love_phrases,happy_phrases,fear_phrases,sad_phrases;

    public main() {
    }

    public ArrayList<String> getHappy_buffer() {
        return happy_buffer;
    }

    public ArrayList<String> getSad_buffer() {
        return sad_buffer;
    }

    public ArrayList<String> getFear_buffer() {
        return fear_buffer;
    }

    public ArrayList<String> getLove_buffer() {
        return love_buffer;
    }

    public ArrayList<String> getAngry_buffer() {
        return angry_buffer;
    }

    public ArrayList<String> getTop_keywords() {
        return top_keywords;
    }

    public int getTotal_happy() {
        return total_happy;
    }

    public int getTotal_sad() {
        return total_sad;
    }

    public int getTotal_fear() {
        return total_fear;
    }

    public int getTotal_love() {
        return total_love;
    }

    public int getTotal_angry() {
        return total_angry;
    }

    public ArrayList<String> getAngry_phrases() {
        return angry_phrases;
    }

    public ArrayList<String> getLove_phrases() {
        return love_phrases;
    }

    public ArrayList<String> getHappy_phrases() {
        return happy_phrases;
    }

    public ArrayList<String> getFear_phrases() {
        return fear_phrases;
    }

    public ArrayList<String> getSad_phrases() {
        return sad_phrases;
    }
}
