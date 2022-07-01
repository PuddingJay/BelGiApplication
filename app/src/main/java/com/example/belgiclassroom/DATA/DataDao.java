package com.example.belgiclassroom.DATA;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public class DataDao {

    @Dao
    public interface KelasDao {

        // Mengambil data kelas berdasarkan id kelas (KID)
        @Query("SELECT * FROM kelas WHERE kid IN (:kelasId)")
        List<Kelas> getKelasByKID(long[] kelasId);

        @Query("SELECT * FROM kelas")
        List<Kelas> getKelas();

        @Insert
        void addKelas(Kelas... kelas);

        @Delete
        void deleteKelas(Kelas kelas);
    }

    @Dao
    public interface TugasDao {
        @Query("SELECT * FROM tugas")
        List<Tugas> getTugas();

        @Insert
        void addTugas(Tugas... tugas);

        @Delete
        void deleteTugas(Tugas tugas);
    }
}
