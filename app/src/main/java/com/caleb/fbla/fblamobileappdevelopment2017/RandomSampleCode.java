package com.caleb.fbla.fblamobileappdevelopment2017;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by User on 1/28/2017.
 */

public class RandomSampleCode extends AppCompatActivity {
    Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        boolean t =isStoragePermissionGranted();

        try {
            Log.e("FTP", "open");





            new Thread(new Runnable() {
                public void run() {
                    try {
                        Manager m = new Manager(getApplicationContext().getFilesDir().getPath());
                        Log.e("FTP", "1");
                        fileUri = m.getPicture("test.jpg");
                        Log.e("FTP", "2");

                        //final ImageView imView = (ImageView) findViewById(R.id.imageView);
                        Log.e("FTP", "3");
                        //imView.setImageResource(0);
                        //imView.setImageURI(fileUri);
                        Log.e("FTP", "4");
                    }
                    catch (Exception e){
                        Log.e("FTP", e + " - error1");
                    }

                }
            }).start();


        }
        catch(Exception e){
            Log.e("FTP", e + " - error");
        }


        SQLiteDatabase db = openOrCreateDatabase(getApplicationContext().getFilesDir().getPath() + "/ProductDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE items" +
                "(" +
                "ID int," +
                "pictureLocation varchar(255)," +
                "price varchar(255)," +
                "title varchar(255)," +
                "description varchar(2000)," +
                "sellerFName varchar(255)" +
                "sellerLName varchar(255)" +
                "email varchar(255)" +
                "sellerPNumber varchar(255)" +
                ");");
        //db.execSQL("INSERT INTO items (pictureLocation, price, description, sellerFName, sellerLName, sellerPNumber)" +
        //      "VALUES ('Cardinal','Tom B. Erichsen','Skagen 21','Stavanger','4006','Norway');");
        //Cursor cursor  = db.query("items", );
        //while(!cursor.isAfterLast()){

        //}
    }


    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //
                //
                // Log.v(TAG,"Permission is granted");
                return true;
            } else {

                //Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            //Log.v(TAG,"Permission is granted");
            return true;
        }
    }
}
