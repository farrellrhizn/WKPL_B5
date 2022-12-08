package com.example.sembakomobile.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import com.example.sembakomobile.R;

public class selamat_datang extends AppCompatActivity{
    private int waktu_loading=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selamat_datang);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(selamat_datang.this, Dashboard.class);
                startActivity(home);
                finish();

            }
        },waktu_loading);
    }
}
