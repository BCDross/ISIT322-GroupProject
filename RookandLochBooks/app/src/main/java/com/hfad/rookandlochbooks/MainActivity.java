package com.hfad.rookandlochbooks;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.hfad.rookandlochbooks.data.RockLochDBOperations;
import com.hfad.rookandlochbooks.data.RookLochDatabaseHelper;
import com.hfad.rookandlochbooks.data.model.LoggedInUser;
import com.hfad.rookandlochbooks.data.session.SessionManager;
import com.hfad.rookandlochbooks.databinding.ActivityMainBinding;
import com.hfad.rookandlochbooks.ui.login.LoginFragment;

import java.util.*;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Added to initialize database and create the tables.
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        Date currentTime = Calendar.getInstance().getTime();
        Boolean sessionIsActive = SessionManager.isSessionActive(currentTime,this);
        RookLochDatabaseHelper dbHelper = new RookLochDatabaseHelper(this);
        dbHelper.getReadableDatabase();

/*
        try {
            //Get reference to the database
            db = dbHelper.getReadableDatabase();
            RockLochDBOperations.insertBook(db, "A Darker sssShade of Magic", "V.E. Schwab", "979-0765376459", "Fantasy", "A story of the four Londons, and a man that can walk between them, and the dangers that come from being the last of a dying race.");
        }catch (SQLiteException e){
            Toast.makeText(getApplicationContext(),"Exception: SQLite DB is unavailable",Toast.LENGTH_LONG).show();

            Log.d(TAG, "DB_Execute Failure: " + e.getMessage().toString());
        } finally {
            //db.close();
        }
*/

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_login, R.id.nav_help, R.id.nav_privacy)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        if (!sessionIsActive) {
            supportFragmentManager.beginTransaction()
                    .replace(binding.drawerLayout.getId(),new LoginFragment(), "Fragment_TAG")
                    .setReorderingAllowed(true)
                    .addToBackStack(null) // name can be null
                    .commit();
            //    Intent intent = new Intent(this, LoginFragment.class);
            //   startActivity(intent);
        }
        String UserID = SessionManager.getUserToken(this);

        //To change the Nav header text, I needed  to instantiate the getHeaderView
        View headerView = navigationView.getHeaderView(0);
        //Once defined, I can now find the text views on the Nav Header section
        TextView loggedInAcct = (TextView) headerView.findViewById(R.id.LoggedInAcct);
        TextView loggedInDisplayName = (TextView) headerView.findViewById(R.id.LoggedInDisplayName);


        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select U.UserID,FirstName,LastName,EmailAddress,Password from User U inner join Security S on S.UserID=U.UserID WHERE "
                        + "U.UserID =?",
                new String[]{UserID.toString()});

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                String insertText = cursor.getString(1) + " " + cursor.getString(2);
                loggedInDisplayName.setText(insertText);
                loggedInAcct.setText(cursor.getString(3));
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            /**
             * It gets into the above IF-BLOCK if anywhere the screen is touched.
             */
            View view = getCurrentFocus();
            if (view instanceof EditText) {
                /**
                 * Now, it gets into the above IF-BLOCK if an EditText is already in focus, and you tap somewhere else
                 * to take the focus away from that particular EditText. It could have 2 cases after tapping:
                 * 1. No EditText has focus
                 * 2. Focus is just shifted to the other EditText
                 */
                Rect outRect = new Rect();
                view.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    view.clearFocus();
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }


}