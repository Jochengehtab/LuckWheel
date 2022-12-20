package com.example.Jochengehtab.wheel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;

public class AllClassesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.activity_all_classes);

        ListView listView = findViewById(R.id.listView);

        SaveManager saveManager = new SaveManager(getApplicationContext(), "ClassNames1");
        saveManager.load(getApplicationContext(), "ClassNames1");

        File file = new File("/data/data/com.example.Jochengehtab.wheel/files", "filename");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object[] objects = saveManager.getAll().values().toArray();

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, objects));

        listView.setOnItemClickListener((parent, view, position, id) -> {
            MainActivity.log(objects[(int) id]);
        });

        Button back = findViewById(R.id.back);
        back.setOnClickListener(v -> startActivity(new Intent(this, RegisterClassActivity.class)));
    }
}