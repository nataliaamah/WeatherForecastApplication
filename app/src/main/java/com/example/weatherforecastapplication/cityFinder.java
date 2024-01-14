package com.example.weatherforecastapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class cityFinder extends AppCompatActivity {

    DatabaseReference databaseLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_finder);
        final EditText editText=findViewById(R.id.searchCity);
        ImageView backButton=findViewById(R.id.backButton);

        databaseLocation = FirebaseDatabase.getInstance().getReference("location");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String newCity= editText.getText().toString();
                if (!TextUtils.isEmpty(newCity)) {

                    String id = databaseLocation.push().getKey();
                    //store artist inside unique id
                    databaseLocation.child(id).setValue(newCity);
                }
                Intent intent=new Intent(cityFinder.this,MainActivity.class);
                intent.putExtra("City",newCity);
                startActivity(intent);



                return false;
            }
        });





    }
}