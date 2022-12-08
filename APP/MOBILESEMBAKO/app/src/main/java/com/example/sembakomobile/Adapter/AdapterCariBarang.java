package com.example.sembakomobile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sembakomobile.Activity.detail_barang;
import com.example.sembakomobile.Model.Retrofit.DataBarang;
import com.example.sembakomobile.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterCariBarang extends RecyclerView.Adapter<AdapterCariBarang.ViewHolder> {
    private Context ctx;
    private List<DataBarang> cariBarangList;

    public void setFilteredList(List<DataBarang> filteredList) {
        this.cariBarangList= filteredList;
        notifyDataSetChanged();
    }

    public interface adapterBarangListener{
        void selectedItemListener(int positionOfItemClicked);
    }
    public AdapterCariBarang(Context ctx, List<DataBarang> dataBarangList) {
        this.ctx = ctx;
        this.cariBarangList = dataBarangList;

    }

    @Override
    @NonNull
    public AdapterCariBarang.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.carditem2,parent,false);
        return new AdapterCariBarang.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCariBarang.ViewHolder holder, int position) {
        DataBarang dm = cariBarangList.get(position);
        Picasso.get()
                .load(cariBarangList.get(position).getGambar()).resize(512,512).centerCrop()
                .into(holder.img_button1);

        holder.nama_card1.setText(dm.getNamaBarang());
        holder.harga_carditem1.setText("Rp. "+dm.getHargaJual());
        holder.stok.setText(dm.getStok());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent =  new Intent(view.getContext(), detail_barang.class);
                view.getContext().startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cariBarangList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton img_button1;
        TextView nama_card1, harga_carditem1, stok;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_button1 = itemView.findViewById(R.id.img_btn_cart);
            nama_card1 = itemView.findViewById(R.id.nama_produk_cart);
            harga_carditem1 = itemView.findViewById(R.id.harga_produk_cart);
            stok = itemView.findViewById(R.id.stok_produk_cart);


//            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//            listener.selectedItemListener(getAdapterPosition());
//        }
    }
}
