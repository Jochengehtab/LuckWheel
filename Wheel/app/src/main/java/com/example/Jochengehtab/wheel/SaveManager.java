package com.example.Jochengehtab.wheel;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Debug;
import android.util.Log;

@SuppressWarnings("unused")
public class SaveManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SaveManager(Context context){
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("Classes", 0);
        this.editor = sharedPreferences.edit();
    }

    public void load(Context context, String name){
        this.sharedPreferences = context.getSharedPreferences(name, 0);
        this.editor = sharedPreferences.edit();
    }

    public void load(SharedPreferences sharedPreferences, String name){
        this.sharedPreferences = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }
    
    public SharedPreferences create(String name){
        this.sharedPreferences = context.getSharedPreferences(name, 0);
        return this.sharedPreferences;
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
