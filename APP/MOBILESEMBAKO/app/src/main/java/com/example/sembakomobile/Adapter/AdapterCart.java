package com.example.sembakomobile.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sembakomobile.Model.Retrofit.DataBarang;
import com.example.sembakomobile.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.CartViewHolder>{

    Context context;
    List<DataBarang> cartList;

    public AdapterCart(Context context, List<DataBarang> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cart_items, parent, false);
        return new CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        DataBarang dm = cartList.get(position);
        Picasso.get()
                .load(cartList.get(position).getGambar()).resize(512,512).centerCrop()
                .into(holder.img_button);

        holder.nama.setText(dm.getNamaBarang());
        holder.harga.setText(dm.getHargaJual());
        holder.stok.setText(dm.getStok());

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        ImageButton img_button;
        TextView nama, harga, stok;
        CheckBox checkBox;
        CardView plusminus;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            img_button = itemView.findViewById(R.id.img_btn_cart);
            nama = itemView.findViewById(R.id.nama_produk_cart);
            harga = itemView.findViewById(R.id.harga_produk_cart);
            stok = itemView.findViewById(R.id.stok_produk_cart);
            checkBox = itemView.findViewById(R.id.checkbox_cart);
            plusminus = itemView.findViewById(R.id.plusminus);
        }
    }
}
