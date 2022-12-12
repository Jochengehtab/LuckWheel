package com.example.Jochengehtab.wheel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

    private final String blue = "#00008B";
    private java.io.File file;

    private LuckyWheel luckyWheel;

    private List<WheelItem> wheelItems;
    private final List<String> members = new ArrayList<>();

    private Button button, registerNewClass;

    //randomColorNumber get initialize in getRandomColor()
    private int max, min, randomColorNumber, winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.activity_main);

        defaultWheel();
        luckyWheel = findViewById(R.id.lwv);


        min = 0;

        button = findViewById(R.id.btnSpin);
        registerNewClass = findViewById(R.id.registerClass);

        Random random = new Random();

        luckyWheel.addWheelItems(wheelItems);

        luckyWheel.setTarget(randomColorNumber);


        luckyWheel.setLuckyWheelReachTheTarget(() -> showDefaultSubtitle("The Winner is: " + wheelItems.get(winner).text + "."));

        EditText editText = findViewById(R.id.input);

        editText.setOnKeyListener((v, keyCode, event) -> {
            if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                if (editText.getText().toString().trim().length() == 0) {
                    showDefaultSubtitle("Bitte gib einen Namen ein.");
                    return false;
                }
                luckyWheel.addWheelItems(wheelItems);
                members.add(editText.getText().toString());
                addAItem(editText.getText().toString());
                showDefaultSubtitle(editText.getText().toString() + " wurde hinzugefügt.");
                editText.setText(null);
            }
            return true;
        });

        button.setOnClickListener(view -> {
            if (members.size() == 0) {
                showDefaultSubtitle("Bitte gib ein paar Namen ein!");
                return;
            }

            max = members.size();

            button.setEnabled(false);
            winner = random.nextInt(max) + min;

            //Show Subtitle
            showDefaultSubtitle("Ausloshung...");

            luckyWheel.rotateWheelTo(winner);
            button.setEnabled(true);
        });

        registerNewClass.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterClassActivity.class);
            startActivity(intent);
        });
    }

    private void defaultWheel() {
        wheelItems = new ArrayList<>();
        wheelItems.add(new WheelItem(Color.parseColor(blue), BitmapFactory.decodeResource(getResources(), R.drawable.blue), "Enter to stat :)"));
    }

    private void addAItem(String name) {
        if (wheelItems.get(0).text.equalsIgnoreCase("Enter to stat :)")) {
            wheelItems.remove(0);
        }
        wheelItems.add(new WheelItem(Color.parseColor(getRandomColor()), BitmapFactory.decodeResource(getResources(), getImageToRandomColor()), name));
    }

    private String getRandomColor() {
        Random random = new Random();
        int minRange = 0, maxRange = 5;
        randomColorNumber = random.nextInt(maxRange + 1 - minRange) + minRange;

        String color, yellow = "#ffff00", red = "#FF0000", green = "#008000", purple = "#9400D3", lightGreen = "#32CD32";

        switch (randomColorNumber) {
            case 1:
                color = yellow;
                break;
            case 2:
                color = red;
                break;
            case 3:
                color = green;
                break;
            case 4:
                color = lightGreen;
                break;
            case 5:
                color = purple;
                break;
            default:
                color = blue;
                break;
        }
        return color;
    }

    private int getImageToRandomColor() {
        int result;
        switch (randomColorNumber) {
            case 1:
                result = R.drawable.yellow;
                break;
            case 2:
                result = R.drawable.red;
                break;
            case 3:
                result = R.drawable.green;
                break;
            case 4:
                result = R.drawable.lightgreen;
                break;
            case 5:
                result = R.drawable.purple;
                break;
            default:
                result = R.drawable.blue;
                break;
        }
        return result;
    }

    private void log(Object msg) {
        Log.println(Log.INFO, "INFO LOG Jochengehtab", String.valueOf(msg));
    }

    private void showDefaultSubtitle(Object msg) {
        Toast.makeText(getApplicationContext(), String.valueOf(msg), Toast.LENGTH_SHORT).show();
    }
}