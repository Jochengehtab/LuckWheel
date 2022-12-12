package com.example.Jochengehtab.wheel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterClassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_class);

        SaveManager saveManager = new SaveManager(getApplicationContext());
        TextView list = findViewById(R.id.output);

        if (!(saveManager.get("Classes") == null)){
            list.append("\n - \t" + saveManager.get("Classes"));
        }

        Button back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        EditText editText = findViewById(R.id.inputadd);
        editText.setOnKeyListener((v, keyCode, event) -> {
            if (!(editText.getText().toString().trim().length() == 0)) {
                saveManager.set("Classes", editText.getText().toString());
                list.append("\n - \t" + saveManager.get("Classes"));
                editText.setText(null);
            }
            return true;
        });
    }
}