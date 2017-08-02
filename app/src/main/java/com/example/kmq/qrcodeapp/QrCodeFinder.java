package com.example.kmq.qrcodeapp;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.vision.CameraSource;
import android.hardware.Camera;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;


public class QrCodeFinder extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_camera);
       final SurfaceView cameraView = (SurfaceView)findViewById(R.id.camera_view);
       final TextView barcodeInfo = (TextView)findViewById(R.id.code_info);
//        );

        BarcodeDetector barcodeDetector =
                new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
                .build();
        final CameraSource cameraSource =
                new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640,480)
                .build();
        cameraView.getHolder().addCallback(new SurfaceHolder.Callback(){
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    cameraSource.start(cameraView.getHolder());
                } catch (IOException ie) {
                    Log.e("CAMERA SOURCE", ie.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {
                    barcodeInfo.post(new Runnable() {    // Use the post method of the TextView
                        public void run() {
                            barcodeInfo.setText(    // Update the TextView
                                    barcodes.valueAt(0).displayValue
                            );
                        }
                    });
                }
            }
        });

//        TextView txtView = (TextView) findViewById(R.id.txtContent);
//        ImageView myImageView = (ImageView) findViewById(R.id.imgview);
//        ImageView showPic = (ImageView) findViewById(R.id.imag);
//
//
//        Bitmap myBitmap = BitmapFactory.decodeResource(
//                getApplicationContext().getResources(),
//                R.drawable.puppy);
//        myImageView.setImageBitmap(myBitmap);
//
//
//        BarcodeDetector detector =
//                new BarcodeDetector.Builder(getApplicationContext())
//                        .setBarcodeFormats(Barcode.QR_CODE)
//                        .build();
//        if(!detector.isOperational()){
//            txtView.setText("Could not set up the detector!");
//            return;
//        }
//        Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
//        SparseArray<Barcode> barcodes = detector.detect(frame);
//
//        Barcode thisCode = barcodes.valueAt(0);
//        txtView.setText(thisCode.rawValue);
//
//
//        Button btn = (Button) findViewById(R.id.buttn);
//        btn.setOnClickListener((new View.OnClickListener() {

//            @Override
//            public void onClick(View view) {
//
//
//            }
  //      }));
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
