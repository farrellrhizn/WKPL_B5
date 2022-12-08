package com.example.sembakomobile;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sembakomobile.Activity.SaveAccount;
import com.example.sembakomobile.Activity.akun;
import com.example.sembakomobile.Activity.info_apk;
import com.example.sembakomobile.Activity.login;
import com.example.sembakomobile.Activity.reset_password;

public class Setting extends Fragment {

    private View nullic;
    private View nulltxthead;
    private View nullbtyes;
    private View nullbtno;
    private AlertDialog alert;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        Button btn1, btn2, btn3, btn4;
        TextView nama1, nama2;

        nama1 = view.findViewById(R.id.nama_setting);
        nama1.setText(SaveAccount.readDataPembeli(getActivity()).getNamaPembeli());

        nama2 = view.findViewById(R.id.username_setting);
        nama2.setText(SaveAccount.readDataPembeli(getActivity()).getUsername());

        btn1 = view.findViewById(R.id.btn_akun);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), akun.class));
            }
        });

        btn2 = view.findViewById(R.id.btn_kmn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), reset_password.class));
            }
        });

        btn3 = view.findViewById(R.id.btn_apk);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), info_apk.class));
            }
        });

        btn4 = view.findViewById(R.id.btn_logout);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog();
            }
        });
        return view;
    }

    SharedPreferences shared;
    @SuppressLint("MissingInflatedId")
    private void alertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.activity_logout, null);
        alertDialogBuilder.setView(view);
        alert = alertDialogBuilder.create();
        alert.show();

        nullic = view.findViewById(R.id.image_set);
        nulltxthead = view.findViewById(R.id.txt_logout1);
        nullbtyes = view.findViewById(R.id.btn_logout1);
        nullbtyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PREF_MODEL", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sharedPreferences.edit();
                editor1.putString("KEY_MODEL_ACCOUNT", "");
                editor1.commit();
                startActivity(new Intent(getActivity(), login.class));
                getActivity().finish();
            }
        });
        nullbtno = view.findViewById(R.id.btn_logout2);
        nullbtno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.cancel();
            }
        });
    }
}