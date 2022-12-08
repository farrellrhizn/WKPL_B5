package com.example.sembakomobile.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sembakomobile.API.ApiClient;
import com.example.sembakomobile.API.ServiceLogin;
//import com.example.sembakomobile.Model.login.Login;
//import com.example.sembakomobile.Model.register.Register;
import com.example.sembakomobile.Model.Retrofit.ResponseModel;
import com.example.sembakomobile.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity implements View.OnClickListener{
    private EditText namaLengkap, alamat, nohp, username, password;
    private Button btnregister;
    TextView tv_login;
    private String NamaLengkap, Alamat, Nohp, Username, Password;
    ServiceLogin ApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        namaLengkap = findViewById(R.id.namaLengkap);
        alamat = findViewById(R.id.alamat);
        nohp = findViewById(R.id.nohp);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        btnregister = findViewById(R.id.btnregister);
        btnregister.setOnClickListener(this);

        tv_login = findViewById(R.id.tv_login);
        tv_login.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnregister:
                NamaLengkap = namaLengkap.getText().toString();
                Alamat = alamat.getText().toString();
                Nohp = nohp.getText().toString();
                Username = username.getText().toString();
                Password = password.getText().toString();
                register(NamaLengkap, Alamat, Nohp, Username, Password);
                break;
            case R.id.tv_login:
                Intent intent = new Intent(this, login.class);
                startActivity(intent);
        }
    }

    private void register(String namaLengkap, String alamat, String nohp, String username, String password) {
        if (username.equals("") || password.equals("") || alamat.equals("") || nohp.equals("") || username.equals("") || password.equals("")){
            Toast.makeText(register.this, "Harap Lengkapi Data", Toast.LENGTH_SHORT).show();
        } else {
            ServiceLogin service = ApiClient.getClient().create(ServiceLogin.class);
            Call<ResponseModel> call = service.registerResponse(namaLengkap, alamat, nohp, username, password);
            call.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    if(response.body().getKode() == 1) {
                        Toast.makeText(register.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(register.this, register_berhasil.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(register.this, "Registrasi Gagal", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(register.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }

    }
}