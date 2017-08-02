package com.example.kmq.qrcodeapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

/**
 * Created by KMQ on 2017. 7. 31..
 */

public class QrCodeImage extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_image);
        ImageView myImageView = (ImageView) findViewById(R.id.imgview);
        ImageView showPic = (ImageView) findViewById(R.id.imag);
        final TextView txtView = (TextView)findViewById(R.id.code_info);



        Bitmap myBitmap = BitmapFactory.decodeResource(
                getApplicationContext().getResources(),
                R.drawable.puppy);
        myImageView.setImageBitmap(myBitmap);


        BarcodeDetector detector =
                new BarcodeDetector.Builder(getApplicationContext())
                        .setBarcodeFormats(Barcode.QR_CODE)
                        .build();
        if (!detector.isOperational()) {
            txtView.setText("Could not set up the detector!");
            return;
        }
        Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
        SparseArray<Barcode> barcodes = detector.detect(frame);

        Barcode thisCode = barcodes.valueAt(0);
        txtView.setText(thisCode.rawValue);


//        Button btn = (Button) findViewById(R.id.buttn);
//        btn.setOnClickListener((new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        }));
    }
    @Override
    public void onStart() {
        super.onStart();}

    @Override
    public void onPause() {
        super.onPause();}
    @Override
    public void onStop() {
        super.onStop();}
    @Override
    public void onDestroy() {
        super.onDestroy();}
}
