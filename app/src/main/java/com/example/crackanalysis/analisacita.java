package com.example.crackanalysis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class analisacita extends AppCompatActivity {

    Button ambilgambar;
    Button analisagambar;
    ImageView poto;
    TextView hasilanalisa;
    TextView aksianalisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analisacita);
        getSupportActionBar().setTitle("Analisa Retakan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        poto = findViewById(R.id.poto);
        ambilgambar=findViewById(R.id.ambilgambar);
        analisagambar = findViewById(R.id.analisagambar);
        hasilanalisa = findViewById(R.id.tekshasilanalisa);
        aksianalisa = findViewById(R.id.teksaksianalisa);

        ambilgambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
            }
        });
        analisagambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getSize();
                hasilanalisa.setText("LEVEL RETAKAN: RUSAK BERAT");
                aksianalisa.setText("Aksi: Lakukan perbaikan pada tiang dan dasar bangunan");
            }
        });
    }

    private void SelectImage(){

        final CharSequence[] items={"Camera","Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(analisacita.this);
        builder.setTitle("Add Image");

        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);

                } else if (items[i].equals("Gallery")) {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    //startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_FILE);
                    startActivityForResult(intent, 0);

                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }

    public  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);

        if(resultCode== Activity.RESULT_OK){

            if(requestCode==1){

                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                poto.setImageBitmap(bmp);

            }else if(requestCode==0){
                Bitmap  mBitmap = null;
                Uri selectedImageUri = data.getData();
                try {
                    mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //poto.setImageURI(selectedImageUri);
                poto.setImageBitmap(mBitmap);

            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }
        return false;
    }

    public void getSize(){
//        Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(),
//                R.drawable.takepicture);
//
//
//        Bitmap bitmap = bitmapOrg;
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//        byte[] imageInByte = stream.toByteArray();
//        long lengthbmp = imageInByte.length;
    }
}
