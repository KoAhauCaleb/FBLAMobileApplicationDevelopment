package com.caleb.fbla.fblamobileappdevelopment2017;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.net.URI;

public class PurchaseActivity extends AppCompatActivity {

    private Item m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        final ImageView image = (ImageView) findViewById(R.id.imageViewPurchase);
        final TextView title = (TextView) findViewById(R.id.textViewTitle);
        final TextView description = (TextView) findViewById(R.id.textViewDescription);
        final TextView email = (TextView) findViewById(R.id.textViewEMail);
        final TextView price = (TextView) findViewById(R.id.textViewPrice);
        final TextView firstName = (TextView) findViewById(R.id.textViewFirstName);
        final TextView lastName = (TextView) findViewById(R.id.textViewLastName);

        m = BuyActivity.chosen;

        title.setText(m.getTitle());
        description.setText(m.getDescription());
        email.setText(m.getEmail());
        price.setText(m.getPrice());
        firstName.setText(m.getFirstName());
        lastName.setText(m.getLastName());
        image.setImageURI(Uri.fromFile(new File(getApplicationContext().getFilesDir().getPath() + "/"+ m.getPictureLocation())));



    }
}
