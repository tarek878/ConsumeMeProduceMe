package com.example.conpro;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    MainActivityViewModel model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ListView listView = findViewById(R.id.list);
        model = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        model.getItemList().observe(this, itemlist -> {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, itemlist
            );
            listView.setAdapter(adapter);
        });

        final Button producer = findViewById(R.id.Producer);
        producer.setOnClickListener((View v) -> {
                runThread("produce", 4000);
        });

        final Button consumer = findViewById(R.id.Consumer);
        consumer.setOnClickListener((View v) -> {
                runThread("consume", 3000);
        });
    }


    private void runThread(String operation, int time){
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(time);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (operation == "produce") model.addItem("list item");
                                else if (operation == "consume") model.removeItem();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }



}
