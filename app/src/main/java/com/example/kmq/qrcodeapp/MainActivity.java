package com.example.kmq.qrcodeapp;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by KMQ on 2017. 7. 31..
 */

public class MainActivity extends AppCompatActivity {

    final Button cameraButton = (Button) findViewById(R.id.cameraButton);
    final Button imageButton = (Button) findViewById(R.id.imageButton);
    TextView textVi = (TextView) findViewById(R.id.barcodeText);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        setContentView(R.layout.activity_main);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCameraIntent = new Intent(MainActivity.this, QrCodeFinder.class);
                startActivityForResult(openCameraIntent, 1);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCameraIntent = new Intent(MainActivity.this, QrCodeImage.class);
                startActivityForResult(openCameraIntent, 1);
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();}
    @Override
    public void onStop() {
        super.onStop();}
    @Override
    public void onDestroy() {
        super.onDestroy();}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result").replace("\n", "").replace("\r", "");
            textVi.setText(scanResult);

        }
    }

}
