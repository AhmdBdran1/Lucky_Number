package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    TextView decTv,resTv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //widget initialization
        decTv=(TextView)findViewById(R.id.tv3);
        resTv=(TextView)findViewById(R.id.tv2);
        btn=(Button)findViewById(R.id.bt2);






        // get values from the first activity
        Intent intent = getIntent();
        String Name = intent.getStringExtra("Name");
        int  lucyNumber = intent.getIntExtra("luckyNumber",0);

        // handle the received values
        resTv.setText(Integer.toString(lucyNumber));
        decTv.setText(Name+" Lucky Number is:");




    }

}