package com.example.belgiclassroom.TAMPILAN.adapter.datamodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.belgiclassroom.BelgiApplication;
import com.example.belgiclassroom.DATA.Kelas;

import java.util.List;

public class KelasViewModel extends ViewModel {

    private MutableLiveData<List<Kelas>> kelas;

    public LiveData<List<Kelas>> getKelas(){
        if (kelas == null) {
            kelas = new MutableLiveData<List<Kelas>>();
            loadKelas();
        }
        return kelas;
    }

    public void loadKelas(){
        kelas.postValue(BelgiApplication.getKelasDao().getKelas());
    }
}
