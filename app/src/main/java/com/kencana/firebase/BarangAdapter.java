package com.kencana.firebase;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewHolder> {
    private ArrayList<Barang> daftarBarang;
    private Context context;
    FirebaseDataListener listener;

    public BarangAdapter(ArrayList<Barang> barangs, Context ctx){
        /**
         * Inisiasi data dan variabel yang akan digunakan
         */
        daftarBarang = barangs;
        context = ctx;
        listener = (FirebaseDBRead) ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Inisiasi View
         * Di tutorial ini kita hanya menggunakan data String untuk tiap item
         * dan juga view nya hanyalah satu TextView
         */
        TextView tvNamabarang;
        TextView tvHargabarang;
        MaterialCardView cvCard;

        ViewHolder(View v) {
            super(v);
            tvNamabarang = (TextView) v.findViewById(R.id.tv_namabarang);
            tvHargabarang = (TextView) v.findViewById(R.id.tv_hargabarang);
            cvCard = (MaterialCardView) v.findViewById(R.id.cv_cardgroup);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        /**
         *  Menampilkan data pada view
         */
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        final String name = daftarBarang.get(position).getNm_barang();
        final String price = daftarBarang.get(position).getHarga_jual();
        holder.cvCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(FirebaseDBDetail.getActIntent((Activity) context).putExtra("data", daftarBarang.get(position)));
            }
        });
        holder.cvCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Delete dan update data
                 */
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_view);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button editButton = (Button) dialog.findViewById(R.id.bt_edit_data);
                Button delButton = (Button) dialog.findViewById(R.id.bt_delete_data);

                //apabila tombol edit diklik
                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                context.startActivity(FirebaseDBCreate.getActIntent((Activity) context).putExtra("data", daftarBarang.get(position)));
                            }
                        }
                );

                //apabila tombol delete diklik
                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                /**
                                 *  Kodingan untuk tutorial Selanjutnya :p Delete data
                                 */
                                dialog.dismiss();
                                listener.onDeleteData(daftarBarang.get(position), position);
                            }
                        }
                );
                return true;
            }
        });
        holder.tvNamabarang.setText(name);
        holder.tvHargabarang.setText("Rp. "+decimalFormat.format(Integer.parseInt(price)));
    }

    @Override
    public int getItemCount() {
        return daftarBarang.size();
    }

    public interface FirebaseDataListener{
        void onDeleteData(Barang barang, int position);
    }
}
