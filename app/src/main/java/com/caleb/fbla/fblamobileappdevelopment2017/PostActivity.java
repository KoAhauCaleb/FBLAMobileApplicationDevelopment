package com.caleb.fbla.fblamobileappdevelopment2017;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;

import Plugins.ImageOrientationUtil;

public class PostActivity extends AppCompatActivity {

    private Uri fileUri;
    private int ID;
    private boolean saved = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final EditText price = (EditText) findViewById(R.id.editTextPrice);
        final EditText description = (EditText) findViewById(R.id.editTextDescription);
        final EditText title = (EditText) findViewById(R.id.editTextTitle);
        final EditText firstName = (EditText) findViewById(R.id.editTextFirstName);
        final EditText lastName = (EditText) findViewById(R.id.editTextLastName);
        final EditText cellPhoneNumber = (EditText) findViewById(R.id.editTextCellPhoneNumber);
        final EditText eMail = (EditText) findViewById(R.id.editTextEMail);



        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db = openOrCreateDatabase(getApplicationContext().getFilesDir().getPath() + "/ProductDB", Context.MODE_PRIVATE, null);

                if(!saved){

                    new Thread( new Runnable() {
                        public void run() {
                            try {
                                Manager m = new Manager(getApplicationContext().getFilesDir().getPath()); //create new manager
                                m.getDB(); //save database to device
                            }
                            catch (Exception e){
                                Log.e("FTP", e + " - error1");
                            }

                        }
                    }).start();

                    db = openOrCreateDatabase(getApplicationContext().getFilesDir().getPath() + "/ProductDB", Context.MODE_PRIVATE, null);
                    Cursor cursor = db.rawQuery("SELECT ID FROM items", null);
                    cursor.moveToLast();


                   final int ID = cursor.getInt(0) + 1;
                }
                saved = true;

                db.execSQL("INSERT INTO items (ID, pictureLocation, price, title, description, sellerFName, sellerLName, email, sellerPNumber)" +
                        "VALUES ('" +
                        ID +//ID
                        "','" +
                        fileUri.toString() +//picture
                        "','" +
                        price.toString() +
                        "','" +
                        title.toString() +
                        "','" +
                        description.toString() +
                        "','" +
                        firstName.toString() +
                        "','" +
                        lastName.toString() +
                        "','" +
                        eMail.toString() +
                        "','" +
                        cellPhoneNumber.toString() +
                        "');");

                new Thread( new Runnable() {
                    public void run() {
                        try {
                            Looper.prepare();
                            Manager m = new Manager(getApplicationContext().getFilesDir().getPath()); //create new manager
                            m.save(getApplicationContext().getFilesDir().getPath() + "/ProductDB", "ProductDB");
                            m.save(getApplicationContext().getFilesDir().getPath() + fileUri, "item" + ID +".jpg");
                        }
                        catch (Exception e){
                            Log.e("FTP", e + " - error1");
                        }

                    }
                }).start();
            }
        });

        final ImageView CamView = (ImageView) findViewById(R.id.CameraView90);//find imageview so it can be accessed in class

        //take new picture if old one is clicked on
        CamView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                fileUri = launchCamera();
            }
        });
    }
    int Rotation;
    @Override
    protected void onResume() {
        super.onResume();//Detect if camera closed
        activityResumed();//run when camera close detected
        //call other functions here if necessary
    }

    public Uri launchCamera() {

        Intent imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//set up camera app open intent
        //variables
        File image;
        File imagesFolder;
        //create folder to put image in
        imagesFolder = new File(getApplicationContext().getFilesDir().getPath(), "/images");//name folder and directory
        imagesFolder.mkdirs(); //create folder of this name at this directory
        //name image taken from camera in this folder
        image = new File(imagesFolder, "item" + ID +".jpg");//name file
        Uri uriSavedImage = Uri.fromFile(image);//convert file to uri
        imageIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);//open camera app
        startActivityForResult(imageIntent, 0);//save image
        return uriSavedImage;//return uri
    }

    protected void activityResumed() {
        setRotationVariables(fileUri);//check rotation needed for image to be right side up
        //set up imageviews
        final ImageView CamView90 = (ImageView) findViewById(R.id.CameraView90);
        final ImageView CamView270 = (ImageView) findViewById(R.id.CameraView270);
        final ImageView CamView180 = (ImageView) findViewById(R.id.CameraView180);
        final ImageView CamView0 = (ImageView) findViewById(R.id.CameraView0);
        //clear imageviews
        CamView90.setImageResource(0);
        CamView270.setImageResource(0);
        CamView180.setImageResource(0);
        CamView0.setImageResource(0);
        //set to imageview based on rotation needed
        if (Rotation == 270)
            CamView90.setImageURI(fileUri);//update CamView to show new image
        else if (Rotation == 90)
            CamView270.setImageURI(fileUri);//update CamView to show new image
        else if (Rotation == 180)
            CamView180.setImageURI(fileUri);//update CamView to show new image
        else
            CamView0.setImageURI(fileUri);//update CamView to show new image
    }

    private void setRotationVariables(Uri uri)
    {
        Rotation = ImageOrientationUtil.getExifRotation(ImageOrientationUtil
                .getFromMediaUri(
                        this,
                        getContentResolver(),
                        uri));
    }
}
