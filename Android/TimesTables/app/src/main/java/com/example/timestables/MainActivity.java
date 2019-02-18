package com.example.timestables;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        SeekBar seekBarTimesTable = findViewById(R.id.seekBarTimesTable);
        final ListView listViewTimesTable = findViewById(R.id.listViewTimesTable);

        seekBarTimesTable.setMax(20);
        seekBarTimesTable.setProgress(10);
        seekBarTimesTable.setClickable(true);
        seekBarTimesTable.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int min = 1;
                int timesTable;

                if (progress < min) {

                    timesTable = min;

                } else {

                    timesTable = progress;

                }

                generateTimesTable(timesTable, listViewTimesTable);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        generateTimesTable(10, listViewTimesTable);

    }

    public void generateTimesTable(int timesTable, ListView listViewTimesTable) {
        ArrayList<String> timesTableContent = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            timesTableContent.add(Integer.toString(i * timesTable));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timesTableContent);
        listViewTimesTable.setAdapter(arrayAdapter);
    }
}
