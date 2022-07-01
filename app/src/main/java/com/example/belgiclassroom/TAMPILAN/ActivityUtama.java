package com.example.belgiclassroom.TAMPILAN;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.belgiclassroom.BelgiApplication;
import com.example.belgiclassroom.DATA.Kelas;
import com.example.belgiclassroom.R;
import com.example.belgiclassroom.TAMPILAN.adapter.KelasAdapter;
import com.example.belgiclassroom.TAMPILAN.adapter.datamodel.KelasViewModel;
import com.example.belgiclassroom.databinding.ActivityUtamaBinding;

import java.util.List;

public class ActivityUtama extends AppCompatActivity {

    private ActivityUtamaBinding binding;
    //public static final int CREATE_KELAS_CODE_REQUEST = 101;
    private static KelasViewModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUtamaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initialize(){
        setSupportActionBar(binding.toolbar);

        binding.listKelas.setLayoutManager(new LinearLayoutManager(this));

        if (BelgiApplication.getUserType() == BelgiApplication.USER_AS_STUDENT){
            binding.fab.setVisibility(View.GONE);
        }

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityUtama.this, ActivityTambahKelas.class);
                startActivity(i);
            }
        });

        model = new ViewModelProvider(this).get(KelasViewModel.class);
        model.getKelas().observe(this, kelas -> {
            binding.listKelas.setAdapter(new KelasAdapter(ActivityUtama.this, kelas));
        });
    }

    public static KelasViewModel getKelasViewModel(){
        return model;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_as_teacher:
                BelgiApplication.setUserType(BelgiApplication.USER_AS_TEACHER);
                recreate();
                break;
            case R.id.action_as_student:
                BelgiApplication.setUserType(BelgiApplication.USER_AS_STUDENT);
                recreate();
                break;
            default: break;
        }
        return true;

    }
}
