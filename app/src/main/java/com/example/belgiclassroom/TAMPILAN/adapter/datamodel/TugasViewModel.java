package com.example.belgiclassroom.TAMPILAN.adapter.datamodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.belgiclassroom.BelgiApplication;
import com.example.belgiclassroom.DATA.Tugas;

import java.util.List;

public class TugasViewModel extends ViewModel {

    private MutableLiveData<List<Tugas>> tugas;

    public LiveData<List<Tugas>> getTugas(){
        if (tugas == null){
            tugas = new MutableLiveData<List<Tugas>>();
            loadTugas();
        }
        return tugas;
    }

    public void loadTugas(){
        tugas.postValue(BelgiApplication.getTugasDao().getTugas());
    }


}
