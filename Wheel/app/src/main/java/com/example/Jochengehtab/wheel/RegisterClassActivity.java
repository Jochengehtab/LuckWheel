package com.example.Jochengehtab.wheel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class RegisterClassActivity extends AppCompatActivity {

    private SaveManager saveManager;
    private boolean isRegisteringANewClass = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.activity_register_class);

        TextView list = findViewById(R.id.output),
        top = findViewById(R.id.top),
        show = findViewById(R.id.show);

        saveManager = new SaveManager(getApplicationContext());

        try {
            for (int i = 0; i != Integer.parseInt(saveManager.get("ClassNames.txt")); i++){
                list.append("\n - \t" + saveManager.get(String.valueOf(i)));
                MainActivity.log("test");
            }
        }
        catch (NumberFormatException e){
            MainActivity.log("The File 'Class' is null.");
            saveManager.set("index.txt", String.valueOf(1));
        }

        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));


        Button allClasses = findViewById(R.id.allClasses);
        allClasses.setOnClickListener(v -> startActivity(new Intent(this, AllClassesActivity.class)));

        Button addClass = findViewById(R.id.addClasses);
        addClass.setOnClickListener(v -> {

            list.setText(null);
            show.setText(null);
            top.setText(R.string.shown);

            isRegisteringANewClass = true;
        });


        EditText editText = findViewById(R.id.inputadd);
        editText.setOnKeyListener((v, keyCode, event) -> {
            MainActivity.log(saveManager.get("index.txt"));
            int count = getIndex();
            if (!(editText.getText().toString().trim().length() == 0)) {
                if (isRegisteringANewClass){
                    saveManager.set("ClassNames.txt", editText.getText().toString() + "\n");
                    top.setText(R.string.bghene);
                    show.setText(R.string.das_sind_die_eigegebenen_namen);
                    isRegisteringANewClass = false;
                    return true;
                }
                saveManager.set(String.valueOf(count), editText.getText().toString());
                list.append("\n - \t" + saveManager.get(String.valueOf(count)));
                editText.setText(null);
                count++;
                saveManager.set("index.txt", String.valueOf(count));
            }
            return true;
        });
    }

    public int getIndex(){
        return Integer.parseInt(saveManager.get("index.txt").trim());
    }
}