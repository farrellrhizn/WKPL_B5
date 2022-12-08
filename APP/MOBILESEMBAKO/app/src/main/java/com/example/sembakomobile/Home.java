package com.example.sembakomobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sembakomobile.API.ApiClient;
import com.example.sembakomobile.API.ServiceLogin;
import com.example.sembakomobile.Activity.SaveAccount;
import com.example.sembakomobile.Activity.cari_barang;
import com.example.sembakomobile.Activity.detail_barang;
import com.example.sembakomobile.Adapter.AdapterBarang;
import com.example.sembakomobile.Model.Retrofit.DataBarang;
import com.example.sembakomobile.Model.Retrofit.ResponseModelBarang;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment {

    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private List<DataBarang> dataBarangList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView nama1;
        Button btn_src;

        nama1 = view.findViewById(R.id.nama);
        nama1.setText("Selamat Datang "+SaveAccount.readDataPembeli(getActivity()).getNamaPembeli());

        btn_src = view.findViewById(R.id.btn_src);
        btn_src.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), cari_barang.class));
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState){
        super.onViewCreated(view, saveInstanceState);
        retrieveData();

        rvData = view.findViewById(R.id.rv_data);
        rvData.setHasFixedSize(true);
        rvData.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    public void detail(int position){
        DataBarang dm = dataBarangList.get(position);
    }

    public void retrieveData() {
        ServiceLogin service1 = ApiClient.getClient().create(ServiceLogin.class);
        Call<ResponseModelBarang> tampilData = service1.tampilBarang();
        tampilData.enqueue(new Callback<ResponseModelBarang>() {
            @Override
            public void onResponse(Call<ResponseModelBarang> call, Response<ResponseModelBarang> response) {
                byte kode = response.body().getKode();
                String message = response.body().getMessage();

                dataBarangList = response.body().getData();
                Gson gson = new Gson();
                System.out.println(gson.toJson(dataBarangList) + " JSON DATA");
                AdapterBarang.adapterBarangListener adapterBarangListener = new AdapterBarang.adapterBarangListener() {
                    @Override
                    public void selectedItemListener(int positionOfItemClicked) {
                        Intent intent  = new Intent(getActivity().getApplicationContext(), detail_barang.class);
                        Gson gso1 = new Gson();
                        String serializeObject = gso1.toJson(dataBarangList.get(positionOfItemClicked));
                        intent.putExtra("KEY_DATA",serializeObject);
                        startActivity(intent);
//                        Toast.makeText(getActivity().getApplicationContext(),
//                                "Click item = " + dataBarangList.get(positionOfItemClicked).getGambar(), Toast.LENGTH_SHORT).show();
                    }
                };
                adData = new AdapterBarang(getContext(), dataBarangList, adapterBarangListener);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModelBarang> call, Throwable t) {
//                Toast.makeText(getActivity(), "Server Error "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}