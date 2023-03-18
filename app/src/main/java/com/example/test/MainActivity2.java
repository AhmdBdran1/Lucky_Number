package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {
    TextView decTv,resTv;
    Button btn;

    private int SHARE_REQUEST_CODE=1202;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //widget initialization
        decTv=(TextView)findViewById(R.id.tv3);
        resTv=(TextView)findViewById(R.id.tv2);
        btn=(Button)findViewById(R.id.bt2);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareScreenAsImage();

            }
        });






        // get values from the first activity
        Intent intent = getIntent();
        String Name = intent.getStringExtra("Name");
        int  lucyNumber = intent.getIntExtra("luckyNumber",0);

        // handle the received values
        resTv.setText(Integer.toString(lucyNumber));
        decTv.setText(Name+" Lucky Number is:");




    }
    private void shareScreenAsImage() {
        try {
            // Capture the screen as a Bitmap
            View rootView = getWindow().getDecorView().getRootView();
            rootView.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
            rootView.setDrawingCacheEnabled(false);

            // Save the Bitmap to a file
            File file = new File(getExternalCacheDir(), "LuckyScreen.png");
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();

            // Share the file as an image
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/png");
            Uri uri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", file);
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            startActivityForResult(Intent.createChooser(shareIntent, "Share via"),SHARE_REQUEST_CODE);


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error sharing screen", Toast.LENGTH_SHORT).show();
        }
    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SHARE_REQUEST_CODE) {
            // Delete the image file from the external cache directory
            File file = new File(getExternalCacheDir(), "LuckyScreen.png");
            file.delete();
        }
    }






}