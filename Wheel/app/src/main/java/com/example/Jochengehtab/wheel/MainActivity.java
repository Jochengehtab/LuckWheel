package com.example.Jochengehtab.wheel;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.WheelItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@SuppressWarnings("unused")
public class MainActivity extends AppCompatActivity {
    private final String yellow = "#ffff00", blue = "#00008B", red = "#FF0000", green = "#008000";
    private LuckyWheel lw;
    List<WheelItem> wheelItems;
    private Button button;
    private int max, min;
    private final List<String> members = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defaultWheel();
        lw = findViewById(R.id.lwv);

        min = 0;

        button = findViewById(R.id.btnSpin);
        Random random = new Random();

        lw.addWheelItems(wheelItems);
        lw.setTarget(1);

        lw.setLuckyWheelReachTheTarget(() -> showDefaultSubtitle("Target Reached"));

        EditText editText = findViewById(R.id.input);

        editText.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER){
                if (editText.getText().toString().trim().length() == 0){
                    showDefaultSubtitle("Bitte gib einen Namen ein.");
                    return false;
                }
                members.add(editText.getText().toString());
                addAItem(editText.getText().toString());
                showDefaultSubtitle(editText.getText().toString() + " wurde hinzugefügt.");
            }
            return false;
        });


        button.setOnClickListener(view -> {

            if (members.size() == 0){
                showDefaultSubtitle("Bitte gib ein paar Namen ein!");
                return;
            }
            max = members.size();

            button.setEnabled(false);
            int randomNumber = random.nextInt(max) + min;

            //Show Subtitle
            showDefaultSubtitle("Ausloshung...");

            lw.rotateWheelTo(1);
            button.setEnabled(true);
        });

    }

    private void defaultWheel(){
        wheelItems = new ArrayList<>();
        wheelItems.add(new WheelItem(Color.parseColor(blue), BitmapFactory.decodeResource(getResources(), R.drawable.blue), "Enter to stat :)"));
    }

    private void addAItem(String name){
        wheelItems.add(new WheelItem(Color.parseColor(getRandomColor()), BitmapFactory.decodeResource(getResources(), R.drawable.yellow), name));
    }

    private String getRandomColor(){
        Random random = new Random();
        int minRange = 0, maxRange = 3, randomNumber = random.nextInt( maxRange + 1 - minRange) + minRange;
        String color = blue;

        switch (randomNumber){
            case 0:
                color = blue;
                break;
            case 1:
                color = yellow;
                break;
            case 2:
                color = red;
                break;
            case 3:
                color = green;
                break;
        }
        return color;
    }

    private void showDefaultSubtitle(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

}