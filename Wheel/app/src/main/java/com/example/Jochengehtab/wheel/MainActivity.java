package com.example.Jochengehtab.wheel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnSpin;
    private CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing views
        btnSpin = findViewById(R.id.btnSpin);

        Random random = new Random();

        // on click listener for btnSpin
        btnSpin.setOnClickListener(view -> {
            // disabling the button so that user
            // should not click on the button
            // while the wheel is spinning
            btnSpin.setEnabled(false);

            // reading random value between 10 to 30
            int spin = random.nextInt(20)+10;

            // since the wheel has 10 divisions, the
            // rotation should be a multiple of
            // 360/10 = 36 degrees
            spin = spin * 36;
            // timer for each degree movement
            int finalSpin = spin;
            timer = new CountDownTimer(finalSpin,1) {

                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    // enabling the button again
                    btnSpin.setEnabled(true);
                }
            }.start();

        });

    }
}