package com.photogallery.camil.photo_gallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE =1;
    static final int REQUEST_VIDEO_CAPTURE = 2;
    private ImageView imageView;
    private Button next_button;
    private Button prev_button;
    private Button camera;
    private ArrayList<Bitmap> images;
    private Bitmap imageBitmap;
    private TextView n_images;
    private int count = 0;
    private Uri video_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.imageView = (ImageView) findViewById(R.id.actual_image);
        this.next_button = (Button) findViewById(R.id.next);
        this.prev_button = (Button) findViewById(R.id.prev);
        this.camera = (Button) findViewById(R.id.camera);
        this.n_images = (TextView) findViewById(R.id.n_photos);
        images = new ArrayList<>();
    }

    public void onCameraClick(View view){
        Toast.makeText(getApplicationContext(),"inicio camara",Toast.LENGTH_LONG).show();
        takePicture();
    }
    public void onVideoClick(View view){
        Toast.makeText(getApplicationContext(),"inicio camara",Toast.LENGTH_LONG).show();
        recordVideo();
    }

    public void recordVideo(){
        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if(videoIntent.resolveActivity(getPackageManager())!= null) {
            startActivityForResult(videoIntent,REQUEST_VIDEO_CAPTURE);
        }
        }

    public void onNextClick(View view){
        if(!images.isEmpty()){
            int index = images.indexOf(this.imageBitmap);
            if(images.size() > index) {
                Bitmap next_image = images.get((index+1)%images.size());
                imageView.setImageBitmap(next_image);
                imageBitmap = next_image;
            }
        }
    }

    public void onPrevClick(View view){
        if(!images.isEmpty()){
            int index = images.indexOf(this.imageBitmap);
            System.out.println(index);
            if(index < 1) index = images.size();
            if(images.size() >= index) {
                    Bitmap prev_image = images.get((index - 1) % images.size());
                    imageView.setImageBitmap(prev_image);
                    imageBitmap = prev_image;
                }

        }
    }

    private void takePicture(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager())!= null){
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            this.imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
            images.add(this.imageBitmap);
            this.count++;
            this.n_images.setText(this.count+"");
        }
        else if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {

            Intent video_activity = new Intent(this,Main2Activity.class);
            video_activity.putExtra("video_url", data.getData().toString());
            startActivity(video_activity);


        }
    }
}