package com.caleb.fbla.fblamobileappdevelopment2017;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.database.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Environment;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.net.Uri;

import android.widget.ImageView;

import java.sql.ResultSet;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //set up layout

        boolean t = isStoragePermissionGranted(); //check if I have permission to use external storage
        boolean c = isCameraPermissionGranted();

        new Thread( new Runnable() {
            public void run() {
                try {
                    Looper.prepare();
                    Manager m = new Manager(getApplicationContext().getFilesDir().getPath()); //create new manager
                    m.getDB();
                    SQLiteDatabase db = openOrCreateDatabase(getApplicationContext().getFilesDir().getPath() + "/ProductDB.sqlite3", Context.MODE_PRIVATE, null);

                    m.save(getApplicationContext().getFilesDir().getPath() + "/ProductDB.sqlite3", "ProductDB.sqlite3");
                    db.close();

                    m.getDB(); //save database to device
                }
                catch (Exception e){
                    Log.e("FTP", e + " - error1");
                }

            }
        }).start();
    }

    public void buyButton(View v){
        startActivity(new Intent(MainActivity.this, PurchaseActivity.class));//start post activity
    }

    public void sellButton(View v){
        startActivity(new Intent(MainActivity.this, PostActivity.class));//start post activity
    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) { //Permission need to be granted if android version more than 23
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) { //check if it has already been granted
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1); //ask user to grant permission
                return false;
            }
        }
        else { //Permission is already granted if android version less than 23
            return true;
        }
    }

    public  boolean isCameraPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) { //Permission need to be granted if android version more than 23
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) { //check if it has already been granted
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1); //ask user to grant permission
                return false;
            }
        }
        else { //Permission is already granted if android version less than 23
            return true;
        }
    }
}
