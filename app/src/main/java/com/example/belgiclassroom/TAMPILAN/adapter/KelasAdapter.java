package com.example.belgiclassroom.TAMPILAN.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.belgiclassroom.BelgiApplication;
import com.example.belgiclassroom.DATA.Kelas;
import com.example.belgiclassroom.R;
import com.example.belgiclassroom.TAMPILAN.ActivityKelas;
import com.example.belgiclassroom.TAMPILAN.ActivityUtama;

import java.util.List;

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.KelasVH> {

    private Context context;
    private List<Kelas> kelasList;

    public KelasAdapter(Context context, List<Kelas> kelasList){
        this.context = context;
        this.kelasList = kelasList;
    }

    @NonNull
    @Override
    public KelasVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.kelas_item, parent, false);
        return new KelasVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KelasVH holder, int position) {
        Kelas kelas = kelasList.get(position);
        holder.iKelas.setText(kelas.getNamaKelas());
        holder.iMapel.setText(kelas.getMapel());

        if (BelgiApplication.getUserType() != BelgiApplication.USER_AS_TEACHER){
            holder.iCopy.setVisibility(View.GONE);
        }

        holder.iKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ActivityKelas.class);
                i.putExtra("kelas", kelas.getNamaKelas());
                context.startActivity(i);
            }
        });

        holder.iCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BelgiApplication.getKelasDao().deleteKelas(kelas);
                ActivityUtama.getKelasViewModel().loadKelas();
                Toast.makeText(context, "Kelas " + kelas.getNamaKelas() + " dihapus", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return kelasList.size();
    }

    public class KelasVH extends RecyclerView.ViewHolder{

        public TextView iKelas, iMapel;
        public ImageView iCopy;

        public KelasVH(@NonNull View itemView) {

            super(itemView);

            iKelas = itemView.findViewById(R.id.i_kelas);
            iMapel = itemView.findViewById(R.id.i_mapel);
            iCopy = itemView.findViewById(R.id.i_copy);
        }
    }
}
