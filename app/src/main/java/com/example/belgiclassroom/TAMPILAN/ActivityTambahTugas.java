package com.example.belgiclassroom.TAMPILAN;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.belgiclassroom.BelgiApplication;
import com.example.belgiclassroom.DATA.Tugas;
import com.example.belgiclassroom.R;
import com.example.belgiclassroom.databinding.ActivityTambahtugasBinding;

import java.util.Objects;

public class ActivityTambahTugas extends AppCompatActivity {

    private ActivityTambahtugasBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTambahtugasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        binding.btnTambahTugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String judulTugas = binding.judulTugas.getText().toString();
                String descTugas = binding.descTugas.getText().toString();

                Tugas tugas = new Tugas();
                tugas.setJudulTugas(judulTugas);
                tugas.setDescTugas(descTugas);

                BelgiApplication.getTugasDao().addTugas(tugas);
                ActivityKelas.getTugasViewModel().loadTugas();

                Toast.makeText(getApplicationContext(), "Tambah Tugas Berhasil", Toast.LENGTH_SHORT).show();

                onBackPressed();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}


