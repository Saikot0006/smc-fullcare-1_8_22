package com.gs.smc_mmc.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.gs.smc_mmc.utils.ConstantUtils;

public class AuthPreference {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public AuthPreference(Context context){
        sharedPreferences = context.getSharedPreferences(ConstantUtils.AdminPreference.FILE_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLoginStatus(int value){
        editor.putInt(ConstantUtils.AdminPreference.STATUS_KEY,value);
        editor.commit();
    }

    public void setLoginStatusValue(boolean value){
        editor.putBoolean("stausValue",value);
        editor.commit();
    }

    public int isAdminLoggedIn(){
        return sharedPreferences.getInt(ConstantUtils.AdminPreference.STATUS_KEY,0);
    }

    public boolean getStatusValue(){
        return sharedPreferences.getBoolean("stausValue",false);
    }

}
