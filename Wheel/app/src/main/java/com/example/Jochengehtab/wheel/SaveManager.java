package com.example.Jochengehtab.wheel;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class SaveManager {

    private final Context context;

    /**
     * @param context The {@link Context}
     */
    public SaveManager(Context context) {
        this.context = context;
    }

    /**
     * Saves automatically
     * @param msg The Message how gets saved
     */
    public void set(String fileName, String msg){
        File file = new File(context.getFilesDir(), fileName);

        if (!file.exists()){
            file = new File(context.getFilesDir(), fileName);
        }

        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.append(msg).append(",").append("\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all out of an File
     * @param fileName The FileName
     * @return Returns a String of the hole File
     */
    public String get(String fileName){
        String result = null;
        File file = new File(context.getFilesDir(), fileName);

        if (!file.exists()){
            file = new File(context.getFilesDir(), fileName);
        }

        try {

            FileReader fileReader = new FileReader(file);
            StringBuilder stringBuilder = new StringBuilder();
            int line;
            while ((line = fileReader.read()) != -1){
                stringBuilder.append((char) line);
            }

            result = Arrays.toString(stringBuilder.toString().split(","));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result == null ? "File is null." :  result;
    }

    public String getFirstLine(String fileName){
        File file = new File(context.getFilesDir(), fileName);
        BufferedReader bufferedReader;
        String text = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            text = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(text);
        String[] strArray = Objects.requireNonNull(text).split(",");
        System.out.println(Arrays.toString(strArray));
        return Arrays.toString(strArray);
    }
}
