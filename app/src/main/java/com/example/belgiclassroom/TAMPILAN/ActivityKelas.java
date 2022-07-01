package com.example.belgiclassroom.TAMPILAN;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.belgiclassroom.BelgiApplication;
import com.example.belgiclassroom.R;
import com.example.belgiclassroom.TAMPILAN.adapter.TugasAdapter;
import com.example.belgiclassroom.TAMPILAN.adapter.datamodel.TugasViewModel;
import com.example.belgiclassroom.databinding.ActivityKelasBinding;

import java.util.Objects;

public class ActivityKelas extends AppCompatActivity {

    private ActivityKelasBinding binding;
    private static TugasViewModel tugasViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityKelasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("kelas"));


        initialize();
    }

    private void initialize(){
        binding.listTugas.setLayoutManager(new LinearLayoutManager(this));

        tugasViewModel = new ViewModelProvider(this).get(TugasViewModel.class);
        tugasViewModel.getTugas().observe(this, tugas -> {
            binding.listTugas.setAdapter(new TugasAdapter(this, tugas));
        });
    }

    public static TugasViewModel getTugasViewModel(){
        return tugasViewModel;
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (BelgiApplication.getUserType() == BelgiApplication.USER_AS_TEACHER){
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.menu_kelas, menu);

            return true;
        }
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_add_tugas:
                Intent i = new Intent(this, ActivityTambahTugas.class);
                startActivity(i);
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
