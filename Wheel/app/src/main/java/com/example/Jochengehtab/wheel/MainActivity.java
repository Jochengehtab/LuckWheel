package com.example.Jochengehtab.wheel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kotlin.jvm.internal.Ref;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private CountDownTimer timer;
    private int max, min;
    private List<String> members = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        min = 10;
        max = 30;

        button = findViewById(R.id.btnSpin);

        Random random = new Random();

        EditText editText = findViewById(R.id.input);

        editText.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER){
                if (editText.getText().toString().trim().length() == 0){
                    showDefaultSubtitle("Bitte gib einen Namen ein.");
                    return false;
                }
                members.add(editText.getText().toString());
                showDefaultSubtitle(editText.getText().toString() + " wurde hinzugefügt.");
            }
            return false;
        });
        button.setOnClickListener(view -> {

            button.setEnabled(false);

            int spin = random.nextInt(max) + min;

            //Show Subtitle
            showDefaultSubtitle("Ausloshung...");

            //Since the wheel has 10 divisions, the
            //Rotation should be a multiple of
            //360/10 = 36 degrees
            spin = spin * 36;

            //Timer for each degree movement
            int finalSpin = spin;
            timer = new CountDownTimer(finalSpin,1) {

                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    //Enabling the Button when it finished
                    button.setEnabled(true);
                }
            }.start();

        });

    }

    private void showDefaultSubtitle(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}