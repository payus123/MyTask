package com.example.mytask.Admin.SharePref;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mytask.R;

import lombok.AllArgsConstructor;


public class SharedPref {
    private  SharedPreferences sharedPreferences;
    private Context context;

    public SharedPref(Context context) {
        this.context = context;
        sharedPreferences =context.getSharedPreferences(context.getResources().getString(R.string.LoginPref),Context.MODE_PRIVATE);

    }

    public void saveToken(String s){
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.Token),s);
        editor.commit();
    }
    public void saveImage(String s){
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.Image),s);
        editor.commit();
    }

    public String getToken(){
        String string =sharedPreferences.getString(context.getResources().getString(R.string.Token),null);
        return string;
    }
    public String getImage(){
        String string =sharedPreferences.getString(context.getResources().getString(R.string.Image),null);
        return string;
    }

    public void saveLogStat(Boolean b){
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.status),b);
        editor.commit();
    }
    public Boolean getLogStat(){
        Boolean aboolean =false;
      aboolean  = sharedPreferences.getBoolean(context.getResources().getString(R.string.status),false);
        return aboolean;
    }
}
