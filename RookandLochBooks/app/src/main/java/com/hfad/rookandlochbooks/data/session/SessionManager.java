package com.hfad.rookandlochbooks.data.session;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.Date;

public class SessionManager {
    static final String SESSION_PREFERENCES="com.hfad.rookandlochbooks.data.session.SESSION_PREFERENCES";
    static final String SESSION_TOKEN = "com.hfad.rookandlochbooks.data.session.SESSION_TOKEN";
    static final String SESSION_DISPLAYNAME = "com.hfad.rookandlochbooks.data.session.SESSION_DISPLAYNAME";
    static final String SESSION_ACCT = "com.hfad.rookandlochbooks.data.session.SESSION_EMAIL";
    static final String SESSION_EXPIRY_TIME = "com.hfad.rookandlochbooks.data.session.SESSION_EXPIRY_TIME";

    public static void startUserSession(Context context, int expiresIn){
        Calendar calendar = Calendar.getInstance();
        Date userLoggedInTime= calendar.getTime();
        calendar.setTime(userLoggedInTime);
        calendar.add(Calendar.SECOND, expiresIn);
        Date expiryTime = calendar.getTime();
        SharedPreferences tokenSharedPreferences = context.getSharedPreferences(SESSION_PREFERENCES,0);
        SharedPreferences.Editor editor = tokenSharedPreferences.edit();
        editor.putLong(SESSION_EXPIRY_TIME,expiryTime.getTime());
        editor.apply();
    }

    public static Boolean isSessionActive(Date currentTime, Context context){
        Date sessionExpireAt = new Date(getExpiryDateFromPreference(context));
        return !currentTime.after(sessionExpireAt);
    }

    private static long getExpiryDateFromPreference (Context context){
        return context.getSharedPreferences(SESSION_PREFERENCES,0).getLong(SESSION_EXPIRY_TIME,0);
    }

    public static void storedUserToken (Context context, String token){
        SharedPreferences.Editor tokenEditor = context.getSharedPreferences(SESSION_PREFERENCES,0).edit();
        tokenEditor.putString(SESSION_TOKEN,token);
        tokenEditor.apply();
    }

    public static String getUserToken (Context context){
        return context.getSharedPreferences(SESSION_PREFERENCES,0).getString(SESSION_TOKEN,"");
    }

    public static void endUserSession (Context context){
        clearStoredData(context);
    }

    private static void clearStoredData(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(SESSION_PREFERENCES,0).edit();
        editor.clear();
        editor.apply();
    }
}
