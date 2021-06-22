package com.hfad.rookandlochbooks.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import com.hfad.rookandlochbooks.R;
import java.net.PasswordAuthentication;
import android.widget.AdapterView;
import android.widget.ListView;
import android.database.sqlite.SQLiteDatabase;
import android.view.View.OnClickListener;

import com.hfad.rookandlochbooks.data.RockLochDBOperations;
import com.hfad.rookandlochbooks.data.RookLochDatabaseHelper;

public class RegisterNewUser extends AppCompatActivity {

    private RockLochDBOperations theDatabase;
    private RookLochDatabaseHelper theDatabaseHelper;
    private View newUserFirstName = findViewById(R.id.newUserFirstName);
    private View newUserLastName = findViewById(R.id.newUserLastName);
    private View newUserEmail = findViewById(R.id.newUserEmail);
    private View newUserPassword = findViewById(R.id.newUserPassword);
    private Button registerNewUserBtn = findViewById(R.id.registerNewUserBtn);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_user);


        registerNewUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    create new database entry for user/auth here
//                theDatabase.insertUser(rookLochBooks, newUserFirstName, newUserLastName, newUserEmail, false);
//                theDatabase.insertSecurity(SQLiteDatabase, newUserPassword);
//                Intent goRegister = new Intent ();
//                startActivity(goRegister);
            }
        });


    }












}