package com.hfad.rookandlochbooks.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.hfad.rookandlochbooks.MainActivity;
import com.hfad.rookandlochbooks.controller.AppContext;
import com.hfad.rookandlochbooks.data.model.LoggedInUser;
import com.hfad.rookandlochbooks.data.RookLochDatabaseHelper;
import com.hfad.rookandlochbooks.data.session.SessionManager;
import com.hfad.rookandlochbooks.ui.login.LoginFragment;
import com.hfad.rookandlochbooks.ui.login.LoginViewModel;


import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private SQLiteDatabase db;
    private Context context;

    public Result<LoggedInUser> login(String username, String password, RookLochDatabaseHelper dbHelper) {

        try {
            // TODO: handle loggedInUser authentication
            db = dbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select U.UserID,FirstName,LastName,EmailAddress,Password from User U inner join Security S on S.UserID=U.UserID WHERE "
                            + "EmailAddress =? AND " + "Password =?",
                    new String[]{username.toString(), password.toString()});

            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    //Delete previous Session
                    SessionManager.endUserSession(AppContext.getAppContext());
                    //Setups new Session with 1800 seconds or 30 minutes defined before session expires.
                    SessionManager.startUserSession(AppContext.getAppContext(), 600);
                    SessionManager.storedUserToken(AppContext.getAppContext(), cursor.getString(0));
                }

            }

            LoggedInUser lUser = new LoggedInUser(cursor.getString(0),cursor.getString(1) + " " + cursor.getString(2));
            return new Result.Success<>(lUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }


    public void logout() {
        // TODO: revoke authentication
    }
}