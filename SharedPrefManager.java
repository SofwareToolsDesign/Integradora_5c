package com.stdesign.bitacorasutd.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.stdesign.bitacorasutd.model.Schedule;
import com.stdesign.bitacorasutd.model.User;
/**
 * Created by Hurón Padilla on 4/15/2018.
 */

public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "stdesignbitacorasutd";

    private static final String KEY_USER_US = "keyuserid";
    private static final String KEY_USER_NAME = "keyusername";
    private static final String KEY_USER_LASTNAME = "keyuserlastname";
    private static final String KEY_USER_TEACHER = "keyuserteacher";

    private static final String KEY_SCHEDULE_SUBJECT = "keyschedulesubject";
    private static final String KEY_SCHEDULE_GROUP = "keyschedulegroup";
    private static final String KEY_SCHEDULE_LAB = "keyschedulelab";

    private static final String KEY_QR_SCANNED = "keyqrscanned";

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_US, user.getUs());
        editor.putString(KEY_USER_NAME, user.getName());
        editor.putString(KEY_USER_LASTNAME, user.getLastname());
        editor.putInt(KEY_USER_TEACHER, user.getIdUser());
        editor.apply();
        return true;
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.getString(KEY_USER_US, null) != null)
            return true;
        return false;
    }

    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_USER_US, null),
                sharedPreferences.getString(KEY_USER_NAME, null),
                sharedPreferences.getString(KEY_USER_LASTNAME, null),
                sharedPreferences.getInt(KEY_USER_TEACHER,0)
        );
    }

    public boolean logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

    public boolean setSchedule(Schedule schedule){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_SCHEDULE_SUBJECT, schedule.getMateria());
        editor.putString(KEY_SCHEDULE_GROUP, schedule.getGrupo());
        editor.putInt(KEY_SCHEDULE_LAB, schedule.getLaboratorio());
        return true;
    }

    public Schedule getSchedule(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Schedule(
                sharedPreferences.getString(KEY_SCHEDULE_SUBJECT,null),
                sharedPreferences.getString(KEY_SCHEDULE_GROUP,null),
                sharedPreferences.getInt(KEY_SCHEDULE_LAB,0),
                "",
                "");
    }

    public boolean setQRScanned(boolean option){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(option == true) editor.putBoolean(KEY_QR_SCANNED, true);
        else editor.putBoolean(KEY_QR_SCANNED, false);

        return true;
    }
    public boolean isQrScanned(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean(KEY_QR_SCANNED,true) == true)return true;
        else return false;
    }
}
