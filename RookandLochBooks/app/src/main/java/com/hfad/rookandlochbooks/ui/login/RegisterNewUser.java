package com.hfad.rookandlochbooks.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hfad.rookandlochbooks.MainActivity;
import com.hfad.rookandlochbooks.R;

import android.widget.Toast;

import com.hfad.rookandlochbooks.data.RookLochDBOperations;
import com.hfad.rookandlochbooks.data.RookLochDatabaseHelper;

public class RegisterNewUser extends AppCompatActivity {




    private SQLiteDatabase db;
    private boolean failedRegistation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_user);

        RookLochDatabaseHelper dbHelper = new RookLochDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        View newUserFirstName = findViewById(R.id.newUserFirstName);
        View newUserLastName = findViewById(R.id.newUserLastName);
        View newUserEmail = findViewById(R.id.newUserEmail);
        View newUserPassword = findViewById(R.id.newUserPassword);
        Button registerNewUserBtn = findViewById(R.id.registerNewUserBtn);




        registerNewUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check to see if the email already exists
                Cursor checkExistingEmail = db.rawQuery("select EmailAddress " +
                                "from User " +
                                "WHERE EmailAddress = ?",
                        new String[]{String.valueOf(newUserEmail)});


                //if the attempted email does not exist... put it in
                if (checkExistingEmail.getCount() < 1) {
                    RookLochDBOperations dbOperations = new RookLochDBOperations();

                    dbOperations.insertUser(db, String.valueOf(newUserFirstName), newUserLastName.toString(), newUserEmail.toString(), false);

                    Cursor cursor2 = db.rawQuery("select UserID " +
                                    "FROM User " +
                                    "WHERE EmailAddress = ?",
                            new String[]{String.valueOf(newUserEmail)});
                    int newUserID = cursor2.getInt(1);
                    dbOperations.insertSecurity(db, newUserPassword.toString(), newUserID);

                    failedRegistation = false;
                }
                else {
                    failedRegistation = true;
                }


                if(failedRegistation == false) {
                    Intent intent = new Intent(RegisterNewUser.this,
                                                            MainActivity.class);
                    Context context = getApplicationContext();
                    CharSequence text =  "Registration Success!";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    startActivity(intent);
                }
                else {
                    Context context = getApplicationContext();
                    CharSequence text = "Email Already Taken. Please Try again.";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }


            }
        });


    }
}