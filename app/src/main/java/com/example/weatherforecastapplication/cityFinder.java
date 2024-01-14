package com.example.weatherforecastapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class cityFinder extends AppCompatActivity {
    ListView listViewLocation;
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    String locationInputed [] = new String[100];
    DatabaseReference databaseLocation;
    String newCity;
    int n=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_finder);
        final EditText editText=findViewById(R.id.searchCity);
        ImageView backButton=findViewById(R.id.backButton);
        listViewLocation = findViewById(R.id.listViewLocation);
        databaseLocation = FirebaseDatabase.getInstance().getReference("location");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list);
        listViewLocation.setAdapter(adapter);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cityFinder.this, MainActivity.class);
                startActivity(intent);
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                newCity= editText.getText().toString();
                if (!TextUtils.isEmpty(newCity)) {

                    String id = databaseLocation.push().getKey();
                    //store artist inside unique id
                    databaseLocation.child(id).setValue(newCity);
                    adapter.add(newCity);
                    rePrint(newCity);
                }
                Intent intent=new Intent(cityFinder.this,MainActivity.class);
                intent.putExtra("City",newCity);
                startActivity(intent);

                return false;
            }

        });

    }

    public void rePrint(String location)
    {
        locationInputed[n]=location;
        n = n+1;
    }

    @Override
    public void onResume(){
        super.onResume();
        int count = 0;
        while(locationInputed[count] != null)
        {
            if(count > 0)
            {
                boolean same = true;
                for(int n=0; n<count;n++)
                {
                    if(!locationInputed[n].equalsIgnoreCase(locationInputed[count]))
                        same = false;
                }
                if(same == false)
                    adapter.add(locationInputed[count]);
            }
            else adapter.add(locationInputed[count]);

            count++;
        }
    }





}