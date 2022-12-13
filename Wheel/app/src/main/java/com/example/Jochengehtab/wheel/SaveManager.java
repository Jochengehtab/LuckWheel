package com.example.Jochengehtab.wheel;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveManager {

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    private Context context;

    public SaveManager(Context context){
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("Classes", 0);
        this.editor = sharedPreferences.edit();
    }

    public SaveManager(Context context, String name){
        this.sharedPreferences = context.getSharedPreferences(name, 0);
        this.editor = sharedPreferences.edit();
    }
    
    public SharedPreferences create(String name){
        this.sharedPreferences = context.getSharedPreferences(name, 0);
        return this.sharedPreferences;
    }

    public String get(String key){
        return sharedPreferences.getString(key, "false");
    }

    public void set(String key, String value){
        editor.putString(key, value);
        save();
    }

    public void save(){
        editor.apply();
    }
}
