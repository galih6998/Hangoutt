package com.example.hangout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference cafeDatabase;
    private RecyclerView rv_cafe;
    FirebaseRecyclerAdapter<ModelCafe, CafeViewHolder> cafeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cafeDatabase = FirebaseDatabase.getInstance().getReference("Hangout");

        rv_cafe = findViewById(R.id.rv_cafe);

        rv_cafe.setHasFixedSize(true);
        rv_cafe.setLayoutManager(new GridLayoutManager(this,2));


        cafeAdapter = new FirebaseRecyclerAdapter<ModelCafe, CafeViewHolder>(
                ModelCafe.class,
                R.layout.item_cafe,
                CafeViewHolder.class,
                cafeDatabase
        ) {
            @Override
            protected void populateViewHolder(CafeViewHolder cafeViewHolder, ModelCafe modelCafe, final int position) {
                cafeViewHolder.setDetails(getApplicationContext(), modelCafe.getNama(), modelCafe.getDeskripsi(),
                        modelCafe.getGambar());

                cafeViewHolder.cardview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this,DetailCafe.class);
                        intent.putExtra("NamaCafe",cafeAdapter.getRef(position).getKey());

                        startActivity(intent);

                    }

                });

            }


        };
        rv_cafe.setAdapter(cafeAdapter);
    }

    public static class CafeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View view , cardview;
        private ItemClickListener itemClickListener;


        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public CafeViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            cardview = itemView.findViewById(R.id.cv_cafe);
            view.setOnClickListener(this);

        }

        public void setDetails(Context context, String nama, String deskripsi, String gambar) {

            TextView txt_nama = view.findViewById(R.id.txt_nama_cafe);
            TextView txt_deskripsi = view.findViewById(R.id.txt_deskripsi_cafe);
            ImageView img_gambar = view.findViewById(R.id.img_cafe);

            txt_nama.setText(nama);
            txt_deskripsi.setText(deskripsi);

            Glide.with(context).load(gambar).into(img_gambar);

        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }
    }
}
