package com.example.Jochengehtab.wheel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class AllClassesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.activity_all_classes);

        ListView listView = findViewById(R.id.listView);

        SaveManager saveManager = new SaveManager(getApplicationContext());

        Object[] objects = {saveManager.get("ClassNames.txt")};

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, objects));

        listView.setOnItemClickListener((parent, view, position, id) -> {
            MainActivity.log(objects[(int) id]);
        });

        ImageButton imageButton = findViewById(R.id.back);
        imageButton.setOnClickListener(v -> startActivity(new Intent(this, RegisterClassActivity.class)));
    }
}