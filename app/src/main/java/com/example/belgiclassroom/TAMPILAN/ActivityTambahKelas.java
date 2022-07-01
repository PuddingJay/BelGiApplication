package com.example.belgiclassroom.TAMPILAN;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.belgiclassroom.BelgiApplication;
import com.example.belgiclassroom.DATA.Kelas;
import com.example.belgiclassroom.databinding.ActivityTambahkelasBinding;

import java.util.Objects;

public class ActivityTambahKelas extends AppCompatActivity {

    private ActivityTambahkelasBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTambahkelasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        binding.btnTambahKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String namaKelas = binding.namaKelas.getText().toString();
                String mapel = binding.mapel.getText().toString();

                Kelas kelas = new Kelas();

                kelas.setNamaKelas(namaKelas);
                kelas.setMapel(mapel);

                BelgiApplication.getKelasDao().addKelas(kelas);

                //merefresh data pada activity utama
                ActivityUtama.getKelasViewModel().loadKelas();

                Toast.makeText(ActivityTambahKelas.this, "Tambah Kelas Berhasil", Toast.LENGTH_SHORT).show();

                onBackPressed();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //kembali ke activity utama

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
