package com.example.sidkathuria14.dishtv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sidkathuria14.dishtv.adapter.MyAdapter;
import com.example.sidkathuria14.dishtv.models.main;

import java.util.ArrayList;

public class TweetsActivity extends AppCompatActivity {
ArrayList<main> tweetResults;
RecyclerView rv;
MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweets);

rv = (RecyclerView)findViewById(R.id.rv);
        ArrayList<main> myList = (ArrayList<main>) getIntent().getSerializableExtra("mylist");

adapter = new MyAdapter(myList,TweetsActivity.this);
rv.setAdapter(adapter);
rv.setLayoutManager(new LinearLayoutManager(TweetsActivity.this));

    }
}
