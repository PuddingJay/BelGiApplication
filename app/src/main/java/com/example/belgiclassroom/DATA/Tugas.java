package com.example.belgiclassroom.DATA;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tugas {

    @PrimaryKey(autoGenerate = true)
    public long idt;

    @ColumnInfo(name = "judul_tugas")
    public String judulTugas;

    @ColumnInfo(name = "desc_tugas")
    public String descTugas;

    public Tugas(){}


    public void setJudulTugas(String judulTugas) {
        this.judulTugas = judulTugas;
    }

    public void setDescTugas(String descTugas) {
        this.descTugas = descTugas;
    }


    public String getJudulTugas() {
        return judulTugas;
    }

    public String getDescTugas() {
        return descTugas;
    }
}
