package com.example.sembakomobile.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sembakomobile.Model.Retrofit.DataBarang;
import com.example.sembakomobile.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.text.NumberFormat;
import java.util.Locale;

public class detail_barang extends AppCompatActivity {
    ImageButton bt_back;
    ImageView img;
    TextView harga;
    TextView nama;
    TextView deskripsi2;
    TextView jenis2;
    TextView stok;
    TextView kurang;
    TextView tambah;
    TextView jumlah;
    TextView total1;
    TextView satuan;
    int count = 0;
    int total = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);
        Intent intent  = getIntent();
        String serializeNameGson = intent.getStringExtra("KEY_DATA");
        Gson gson = new Gson();
        Type typeAccount = new TypeToken<DataBarang>(){}.getType();
        DataBarang dataBarang = gson.fromJson(serializeNameGson,typeAccount);
        img = findViewById(R.id.img_produk);
        harga = findViewById(R.id.txt_harga);
        nama = findViewById(R.id.txt_nama);
        jenis2 = findViewById(R.id.nama_jenis);
        deskripsi2 = findViewById(R.id.nama_deskripsi);
        stok = findViewById(R.id.img_stok);
        kurang = findViewById(R.id.kurang);
        tambah = findViewById(R.id.tambah);
        jumlah = findViewById(R.id.jumlah);
        total1 = findViewById(R.id.total);
        satuan = findViewById(R.id.img_satuan);



        harga.setText("Rp. "+dataBarang.getHargaJual());
        nama.setText(dataBarang.getNamaBarang());
        jenis2.setText(dataBarang.getJenis_barang());
        deskripsi2.setText(dataBarang.getDeskripsi());
        stok.setText(dataBarang.getStok());
        satuan.setText(dataBarang.getSatuan());
//        total1.setText(dataBarang.getHargaJual());

        Picasso.get().load(dataBarang.getGambar()).resize(512,512).centerCrop().into(img);

        bt_back = findViewById(R.id.btn_bck);
        bt_back.setOnClickListener(view -> onBackPressed());

        kurang.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View v) {
                if (count <= 1) count=1;
                else
                count--;
                jumlah.setText(""+count);
                total = Integer.parseInt(jumlah.getText().toString()) * Integer.parseInt(dataBarang.getHargaJual());
                total1.setText("Total : Rp. "+total);
            }
        });

        tambah.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View v) {
                if (count >= Integer.parseInt(stok.getText().toString()));
                else
                count++;
                jumlah.setText(""+count);
                total = Integer.parseInt(jumlah.getText().toString()) * Integer.parseInt(dataBarang.getHargaJual());
                total1.setText("Total : Rp. "+total);
            }
});
}
//    private String formatRupiah(Double number){
//        Locale localeID = new Locale("in", "ID");
//        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
//        return formatRupiah.format(number);
//    }
}