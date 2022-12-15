package com.example.Jochengehtab.wheel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterClassActivity extends AppCompatActivity {

    private SaveManager saveManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.activity_register_class);

        TextView list = findViewById(R.id.output);
        saveManager = new SaveManager(getApplicationContext());

        try {
            for (int i = 0; i != Integer.parseInt(saveManager.get("index")); i++){
                list.append("\n - \t" + saveManager.get(String.valueOf(i)));
                MainActivity.log("test");
            }
        }
        catch (NumberFormatException e){
            MainActivity.log("The File 'Class' is null.");
            saveManager.set("index", 0);
        }


        Button back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        EditText editText = findViewById(R.id.inputadd);
        editText.setOnKeyListener((v, keyCode, event) -> {
            int count = getIndex();
            if (!(editText.getText().toString().trim().length() == 0)) {
                saveManager.set(String.valueOf(count), editText.getText().toString());
                list.append("\n - \t" + saveManager.get(String.valueOf(count)));
                editText.setText(null);
                count++;
                saveManager.set("index", count);
            }
            return true;
        });
    }

    public int getIndex(){
        return Integer.parseInt(saveManager.get("index"));
    }
}