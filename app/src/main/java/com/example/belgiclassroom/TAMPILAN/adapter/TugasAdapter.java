package com.example.belgiclassroom.TAMPILAN.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.belgiclassroom.BelgiApplication;
import com.example.belgiclassroom.DATA.Tugas;
import com.example.belgiclassroom.R;
import com.example.belgiclassroom.TAMPILAN.ActivityKelas;

import java.util.List;

public class TugasAdapter extends RecyclerView.Adapter<TugasAdapter.TugasVH>{

    private Context context;
    private List<Tugas> tugasList;

    public TugasAdapter(Context context, List<Tugas> tugasList){
        this.context = context;
        this.tugasList = tugasList;

    }

    @NonNull
    @Override
    public TugasVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.tugas_item, parent, false);
        return new TugasVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TugasVH holder, int position) {
        Tugas tugas = tugasList.get(position);
        holder.iJudul.setText(tugas.getJudulTugas());
        holder.iDesc.setText(tugas.getDescTugas());

        holder.iJudul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteAlert(tugas);
            }
        });
    }

    private void showDeleteAlert(Tugas tugas){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(tugas.getJudulTugas());
        builder.setMessage(tugas.getDescTugas());
        builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (BelgiApplication.getUserType() == BelgiApplication.USER_AS_TEACHER){
                    BelgiApplication.getTugasDao().deleteTugas(tugas);
                    ActivityKelas.getTugasViewModel().loadTugas();
                }else {
                    Toast.makeText(context, "Tidak diizinkan menghapus", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNeutralButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    @Override
    public int getItemCount() {
        return tugasList.size();
    }

    public static class TugasVH extends RecyclerView.ViewHolder{

        public CardView iCard;
        public TextView iJudul, iDesc;

        public TugasVH(@NonNull View itemView) {
            super(itemView);

            iCard = itemView.findViewById(R.id.i_card_tugas);
            iJudul = itemView.findViewById(R.id.i_judul_tugas);
            iDesc = itemView.findViewById(R.id.i_desc_tugas);
        }
    }
}
