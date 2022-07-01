package com.example.belgiclassroom.DATA;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Kelas {

    @PrimaryKey(autoGenerate = true)
    public long kid;

    @ColumnInfo(name = "nama_kelas")
    public String namaKelas;

    @ColumnInfo(name = "mapel")
    public String mapel;

    public Kelas(){}

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public void setMapel(String mapel) {
        this.mapel = mapel;
    }

    public long getKid() {
        return kid;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public String getMapel() {
        return mapel;
    }
}
