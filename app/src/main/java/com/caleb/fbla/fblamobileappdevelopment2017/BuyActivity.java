package com.caleb.fbla.fblamobileappdevelopment2017;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class BuyActivity extends AppCompatActivity {

    private Item[] items = new Item[10];
    private Cursor cursor;
    public static Item chosen;

    private final ImageView  view1 = (ImageView) findViewById(R.id.imageViewPurchase);
    private final ImageView  view2 = (ImageView) findViewById(R.id.imageView2);
    private final ImageView  view3 = (ImageView) findViewById(R.id.imageView3);
    private final ImageView  view4 = (ImageView) findViewById(R.id.imageView4);
    private final ImageView  view5 = (ImageView) findViewById(R.id.imageView5);
    private final ImageView  view6 = (ImageView) findViewById(R.id.imageView6);
    private final ImageView  view7 = (ImageView) findViewById(R.id.imageView7);
    private final ImageView  view8 = (ImageView) findViewById(R.id.imageView8);
    private final ImageView  view9 = (ImageView) findViewById(R.id.imageView9);
    private final ImageView  view10 = (ImageView) findViewById(R.id.imageView10);
    private final TextView tView1 = (TextView) findViewById(R.id.textView1);
    private final TextView tView2 = (TextView) findViewById(R.id.textView2);
    private final TextView tView3 = (TextView) findViewById(R.id.textView3);
    private final TextView tView4 = (TextView) findViewById(R.id.textView4);
    private final TextView tView5 = (TextView) findViewById(R.id.textView5);
    private final TextView tView6 = (TextView) findViewById(R.id.textView6);
    private final TextView tView7 = (TextView) findViewById(R.id.textView7);
    private final TextView tView8 = (TextView) findViewById(R.id.textView8);
    private final TextView tView9 = (TextView) findViewById(R.id.textView9);
    private final TextView tView10 = (TextView) findViewById(R.id.textView10);
    private final TextView tView11 = (TextView) findViewById(R.id.textView11);
    private final TextView tView12 = (TextView) findViewById(R.id.textView12);
    private final TextView tView13 = (TextView) findViewById(R.id.textView13);
    private final TextView tView14 = (TextView) findViewById(R.id.textView14);
    private final TextView tView15 = (TextView) findViewById(R.id.textView15);
    private final TextView tView16 = (TextView) findViewById(R.id.textView16);
    private final TextView tView17 = (TextView) findViewById(R.id.textView17);
    private final TextView tView18 = (TextView) findViewById(R.id.textView18);
    private final TextView tView19 = (TextView) findViewById(R.id.textView19);
    private final TextView tView20 = (TextView) findViewById(R.id.textView20);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

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
        new Thread( new Runnable() {
            public void run() {
                try {
                    Manager m = new Manager(getApplicationContext().getFilesDir().getPath()); //create new manager
                    m.save(getApplicationContext().getFilesDir().getPath() + "/ProductDB", "ProductDB"); //save database to device
                }
                catch (Exception e){
                    Log.e("FTP", e + " - error1");
                }

            }
        }).start();
        cursor = db.rawQuery("SELECT * FROM items", null);
        cursor.moveToFirst();
        setNextTenItems();
        viewItems();
    }

    public void viewItems(){
        view1.setImageURI(Uri.fromFile(new File(items[0].getPictureLocation())));
        view2.setImageURI(Uri.fromFile(new File(items[1].getPictureLocation())));
        view3.setImageURI(Uri.fromFile(new File(items[2].getPictureLocation())));
        view4.setImageURI(Uri.fromFile(new File(items[3].getPictureLocation())));
        view5.setImageURI(Uri.fromFile(new File(items[4].getPictureLocation())));
        view6.setImageURI(Uri.fromFile(new File(items[5].getPictureLocation())));
        view7.setImageURI(Uri.fromFile(new File(items[6].getPictureLocation())));
        view8.setImageURI(Uri.fromFile(new File(items[7].getPictureLocation())));
        view9.setImageURI(Uri.fromFile(new File(items[8].getPictureLocation())));
        view10.setImageURI(Uri.fromFile(new File(items[9].getPictureLocation())));
        tView1.setText(items[0].getTitle());
        tView2.setText(items[0].getPrice());
        tView3.setText(items[1].getTitle());
        tView4.setText(items[1].getPrice());
        tView5.setText(items[2].getTitle());
        tView6.setText(items[2].getPrice());
        tView7.setText(items[3].getTitle());
        tView8.setText(items[3].getPrice());
        tView9.setText(items[4].getTitle());
        tView10.setText(items[4].getPrice());
        tView11.setText(items[5].getTitle());
        tView12.setText(items[5].getPrice());
        tView13.setText(items[6].getTitle());
        tView14.setText(items[6].getPrice());
        tView15.setText(items[7].getTitle());
        tView16.setText(items[7].getPrice());
        tView17.setText(items[8].getTitle());
        tView18.setText(items[8].getPrice());
        tView19.setText(items[9].getTitle());
        tView20.setText(items[9].getPrice());
    }

    public void setNextTenItems(){

        for(int i = 0; i < 10; i++) {
            cursor.moveToNext();
            items[i] = new Item(cursor.getString(1), cursor.getString(3), cursor.getString(2), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
        }

        new Thread( new Runnable() {
            public void run() {
                try {
                    Manager m = new Manager(getApplicationContext().getFilesDir().getPath()); //create new manager
                    m.getPicture(items[0].getPictureLocation());//save database to device
                    m.getPicture(items[1].getPictureLocation());
                    m.getPicture(items[2].getPictureLocation());
                    m.getPicture(items[3].getPictureLocation());
                    m.getPicture(items[4].getPictureLocation());
                    m.getPicture(items[5].getPictureLocation());
                    m.getPicture(items[6].getPictureLocation());
                    m.getPicture(items[7].getPictureLocation());
                    m.getPicture(items[8].getPictureLocation());
                    m.getPicture(items[9].getPictureLocation());
                }
                catch (Exception e){
                    Log.e("FTP", e + " - error1");
                }

            }
        }).start();
    }

    public void setPreviousTenItems(){
        for(int i = 9; i >= 0; i--) {
            cursor.moveToPrevious();
            items[i] = new Item(cursor.getString(1), cursor.getString(3), cursor.getString(2), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
        }
    }

    public void option1(View v){
        chosen = items[0];
        startActivity(new Intent(BuyActivity.this, PurchaseActivity.class));//start purchase activity
    }
    public void option2(View v){
        chosen = items[1];
        startActivity(new Intent(BuyActivity.this, PurchaseActivity.class));//start purchase activity
    }
    public void option3(View v){
        chosen = items[2];
        startActivity(new Intent(BuyActivity.this, PurchaseActivity.class));//start purchase activity
    }
    public void option4(View v){
        chosen = items[3];
        startActivity(new Intent(BuyActivity.this, PurchaseActivity.class));//start purchase activity
    }
    public void option5(View v){
        chosen = items[4];
        startActivity(new Intent(BuyActivity.this, PurchaseActivity.class));//start purchase activity
    }
    public void option6(View v){
        chosen = items[5];
        startActivity(new Intent(BuyActivity.this, PurchaseActivity.class));//start purchase activity
    }
    public void option7(View v){
        chosen = items[6];
        startActivity(new Intent(BuyActivity.this, PurchaseActivity.class));//start purchase activity
    }
    public void option8(View v){
        chosen = items[7];
        startActivity(new Intent(BuyActivity.this, PurchaseActivity.class));//start purchase activity
    }
    public void option9(View v){
        chosen = items[8];
        startActivity(new Intent(BuyActivity.this, PurchaseActivity.class));//start purchase activity
    }
    public void option10(View v){
        chosen = items[9];
        startActivity(new Intent(BuyActivity.this, PurchaseActivity.class));//start purchase activity
    }
}
