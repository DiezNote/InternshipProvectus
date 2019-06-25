package com.dieznote.internshipprovectus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class About extends AppCompatActivity {

    TextView version;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        version=(TextView)findViewById(R.id.textViewVers);
        String versionString = BuildConfig.VERSION_NAME;
        version.setText("ver. "+versionString);
    }
    public void onClick(View view){
        finish();
    }

}
