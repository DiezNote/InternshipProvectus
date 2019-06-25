package com.dieznote.internshipprovectus;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;

public class SecondActivity extends AppCompatActivity {

    Item personInfo;
    String jsonObject;
    ImageView imageView;
    TextView name,email,bd,age,street,city,postcode,phoneNumber,cell,login,password;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView = findViewById(R.id.imageView);
        name = findViewById(R.id.textViewName);
        email = findViewById(R.id.textViewEmail);
        bd = findViewById(R.id.textViewBD);
        age = findViewById(R.id.textViewAge);
        street = findViewById(R.id.textViewStreet);
        city = findViewById(R.id.textViewCity);
        postcode = findViewById(R.id.textViewPostcode);
        phoneNumber = findViewById(R.id.textViewPhone);
        cell = findViewById(R.id.textViewCell);
        login = findViewById(R.id.textViewLogin);
        password = findViewById(R.id.textViewPassword);
        back = findViewById(R.id.buttonBack);

        jsonObject = getIntent().getStringExtra("personInfo");
        personInfo = new Gson().fromJson(jsonObject,Item.class);

        Picasso.get().load(personInfo.results.get(0).picture.large).resize(500,500).into(imageView);
        name.setText(personInfo.results.get(0).name.title +" "+Character.toUpperCase(personInfo.results.get(0).name.first.charAt(0))+personInfo.results.get(0).name.first.substring(1).toLowerCase()+" "+Character.toUpperCase(personInfo.results.get(0).name.last.charAt(0))+personInfo.results.get(0).name.last.substring(1).toLowerCase());
        email.setText(personInfo.results.get(0).email);
        bd.setText(personInfo.results.get(0).dob.date.substring(0,10));
        age.setText("( "+personInfo.results.get(0).dob.age+" y/o )");
        street.setText(Character.toUpperCase(personInfo.results.get(0).location.street.charAt(0))+personInfo.results.get(0).location.street.substring(1).toLowerCase());

        city.setText(Character.toUpperCase(personInfo.results.get(0).location.city.charAt(0))+personInfo.results.get(0).location.city.substring(1).toLowerCase());
        postcode.setText(personInfo.results.get(0).location.postcode);
        phoneNumber.setText(personInfo.results.get(0).phone);
        cell.setText(personInfo.results.get(0).cell);
        login.setText(personInfo.results.get(0).login.username);
        password.setText(personInfo.results.get(0).login.password);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareintent = new Intent(Intent.ACTION_SEND);
                shareintent.setType("text/plain");

                String sharevia = "Share via: ";
                String shareSub = "Name: "+"\n"+personInfo.results.get(0).name.title +" "+Character.toUpperCase(personInfo.results.get(0).name.first.charAt(0))+personInfo.results.get(0).name.first.substring(1).toLowerCase()+" "+Character.toUpperCase(personInfo.results.get(0).name.last.charAt(0))+personInfo.results.get(0).name.last.substring(1).toLowerCase()+". "+"\n"+"\n"
                        +"Email address: "+"\n"+personInfo.results.get(0).email+". "+"\n"+"\n"
                        +"Birthday: "+"\n"+personInfo.results.get(0).dob.date.substring(0,10)+". "+"\n"+"\n"
                        +"Address: "+"\n"+"street - "+Character.toUpperCase(personInfo.results.get(0).location.street.charAt(0))+personInfo.results.get(0).location.street.substring(1).toLowerCase()+". "+"\n"
                        +"city - "+Character.toUpperCase(personInfo.results.get(0).location.city.charAt(0))+personInfo.results.get(0).location.city.substring(1).toLowerCase()+". "+"\n"
                        +"postcode - "+personInfo.results.get(0).location.postcode+". "+"\n"+"\n"
                        +"Phone: "+" "+personInfo.results.get(0).phone+" "+"\n"+"Cell: "+" "+personInfo.results.get(0).cell+". "+"\n"+"\n"
                        +"Login: "+personInfo.results.get(0).login.username+"\n"
                        +"Password: "+personInfo.results.get(0).login.password+"\n"+"\n"
                        +"Additional info: "+"\n"
                        +"gender: "+personInfo.results.get(0).gender+"\n"
                        +"latitude: "+personInfo.results.get(0).location.coordinates.latitude+"\n"
                        +"longitude: "+personInfo.results.get(0).location.coordinates.longitude+"\n"
                        +"timezone: "+personInfo.results.get(0).location.timezone.description+"( "+personInfo.results.get(0).location.timezone.offset+" )"+"\n";
                shareintent.putExtra(Intent.EXTRA_TEXT,shareSub);
                startActivity(Intent.createChooser(shareintent,sharevia));
            }
        });

    }
    public void onClick(View view){
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent intent2 = new Intent(SecondActivity.this, About.class);
            startActivity(intent2);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
