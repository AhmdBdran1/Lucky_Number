package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // widgets initialization

        editText=(EditText)findViewById(R.id.et1);
        textView = (TextView) findViewById(R.id.tv1);
        btn=(Button)findViewById(R.id.bt1);




        // define onClickListener for the sign in button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name=editText.getText().toString();
                Random rand=new Random();

                int upperbound = 1000;
                // Generating random values from 0 - 1000 using nextInt()
                int int_random = rand.nextInt(upperbound);

                Intent intent=new Intent(MainActivity.this,MainActivity2.class);

                //send the name and the random number to the second activity
                intent.putExtra("Name",Name);
                intent.putExtra("luckyNumber",int_random);


                //move to the second activity
                startActivity(intent);





            }
        });







        //design text color as gradient linear color

        String s =textView.getText().toString();
        TextPaint paint = textView.getPaint();
        float width = paint.measureText(s);
        Shader textShader = new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        Color.parseColor("#2A3426"),
                        Color.parseColor("#30651B"),
                        Color.parseColor("#4DBB21"),

                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);










    }
}