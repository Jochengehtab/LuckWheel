package com.example.Jochengehtab.wheel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterClassActivity extends AppCompatActivity {

    private int index;
    private SaveManager saveManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.activity_register_class);

        TextView list = findViewById(R.id.output);
        saveManager = new SaveManager(getApplicationContext());

        MainActivity.log(saveManager.get("index"));
        if (saveManager.get("index") == null){
            index = Integer.parseInt(saveManager.get("index"));
        }
        else{
            index = 0;
        }

        if (!(saveManager.get("Classes").equalsIgnoreCase("false"))){
            MainActivity.log(index);
            for (int i = 1; index >= i; i++){
                list.append("\n - \t" + saveManager.get(String.valueOf(i)));
                MainActivity.log("Bitte " + saveManager.get(String.valueOf(i)));
            }
        }

        Button back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        EditText editText = findViewById(R.id.inputadd);
        editText.setOnKeyListener((v, keyCode, event) -> {
            if (!(editText.getText().toString().trim().length() == 0)) {
                saveManager.set(String.valueOf(index), editText.getText().toString());
                list.append("\n - \t" + saveManager.get(String.valueOf(index)));
                editText.setText(null);
                index++;
            }
            return true;
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveManager.set("index", index);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveManager.set("index", index);
    }
}