package com.example.hangout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailCafe extends AppCompatActivity {

    public TextView txt_nama, txt_deskripsi;
    public ImageView img_cafe;
    public FirebaseDatabase database;
    public DatabaseReference Hangout;
    String cafeId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cafe);

        database = FirebaseDatabase.getInstance();
        Hangout = database.getReference("Hangout");
        txt_nama = findViewById(R.id.txt_detail_nama);
        txt_deskripsi = findViewById(R.id.txt_detail_deskripsi);
        img_cafe = findViewById(R.id.img_detail_cafe);

        if (getIntent() != null) {
            cafeId = getIntent().getStringExtra("NamaCafe");
            if (!cafeId.isEmpty()) {
                getDetailCafe(cafeId);
            }
        }

    }

    private void getDetailCafe(String cafeId) {
        Hangout.child(cafeId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ModelCafe modelCafe = dataSnapshot.getValue(ModelCafe.class);
                txt_nama.setText(modelCafe.getNama());
                txt_deskripsi.setText(modelCafe.getDeskripsi());

                Glide.with(getBaseContext()).load(modelCafe.getGambar()).into(img_cafe);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
