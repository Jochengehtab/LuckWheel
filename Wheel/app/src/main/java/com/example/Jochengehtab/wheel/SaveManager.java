package com.example.Jochengehtab.wheel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import java.io.File;
import java.util.Map;

@SuppressWarnings({"unused", "unchecked"})
public class SaveManager {

    private File file;
    private Context context;

    public SaveManager(Context context){

    }

    public void load(Context context, String name){
        this.sharedPreferences = context.getSharedPreferences(name, 0);
        this.editor = context.getSharedPreferences(name, 0).edit();
    }

    public void load(SharedPreferences sharedPreferences, String name){
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }
    public Map<String, String> getAll() {
        return (Map<String, String>) sharedPreferences.getAll();
    }

    public String get(String key){
        return sharedPreferences.getString(key, "false");
    }

    public void set(String key, Object value){
        editor.putString(key, String.valueOf(value));
        save();
    }

    public void save(){
        editor.apply();
    }
}
