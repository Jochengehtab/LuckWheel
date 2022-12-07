package com.example.Jochengehtab.wheel;

import android.os.Build;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class File {

    public String getRandomString(){
        int leftLimit = 48, rightLimit = 122, targetStringLength = 10;
        Random random = new Random();

        String randomString;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            randomString = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        }
        else{
            randomString = null;
        }

        return randomString;
    }

    /**
     * Returns the First possible Material of a file
     * @param file The File where get road
     * @return Returns the First Material
     */
    public static ArrayList<String> get(java.io.File file) {
        ArrayList<String> list = new ArrayList<>();

        if (!file.canRead() || !file.isFile()){
            System.exit(0);
        }

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            String zeile;
            while ((zeile = in.readLine()) != null) {
                list.add(zeile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return list;
    }

    /**
     * Write a String into a File
     * @param inFile The InputFile
     * @param value The Value who get in the Vile
     */
    public static void writeInFile(java.io.File inFile, String value) {
        try (FileWriter fileWriter = new FileWriter(inFile)) {
            fileWriter.write(value);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}