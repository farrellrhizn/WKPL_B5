package com.example.sembakomobile.API;

import com.example.sembakomobile.Model.Retrofit.ResponseModel;
import com.example.sembakomobile.Model.Retrofit.ResponseModelBarang;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceLogin {
    @FormUrlEncoded
    @POST("retrieve.php")
    Call<ResponseModel> loginResponse(
            @Field("Username") String Username,
            @Field("Password") String Password
    );

    @FormUrlEncoded
    @POST("cari_barang.php")
    Call<ResponseModelBarang> cariBarangSpesifik(
            @Field("NamaBarang") String NamaBarang
    );

    @FormUrlEncoded
    @POST("create.php")
    Call<ResponseModel> registerResponse(
            @Field("NamaPembeli") String NamaPembeli,
            @Field("Alamat") String Alamat,
            @Field("no_hp") String no_hp,
            @Field("Username") String Username,
            @Field("Password") String Password
    );

    @FormUrlEncoded
    @POST("update_akun.php")
    Call<ResponseModel> updateResponse(
            @Field("idPembeli") String idPembeli,
            @Field("NamaPembeli") String NamaPembeli,
            @Field("Alamat") String Alamat,
            @Field("no_hp") String no_hp,
            @Field("Username") String Username
    );

    @FormUrlEncoded
    @POST("update_pw.php")
    Call<ResponseModel> updateResponsePw(
            @Field("idPembeli") String idPembeli,
            @Field("Password") String Password);

    @GET("barang.php")
    Call<ResponseModelBarang> tampilBarang(
    );

//    @FormUrlEncoded
//    @POST("update_pw.php")
//    Call<ResponseModel> passwordValidasi(
//            @Field("Password") String Password
//    );
}
