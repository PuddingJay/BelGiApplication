package com.example.belgiclassroom.DATA;

import androidx.room.Database;
import androidx.room.RoomDatabase;


public class AppDatabase {

    @Database(entities = Kelas.class, version = 1, exportSchema = false)
    public static abstract class DataKelas extends RoomDatabase{
        public abstract DataDao.KelasDao kelasDao();
    }

    @Database(entities = Tugas.class, version = 1, exportSchema = false)
    public static abstract class DataTugas extends RoomDatabase{
        public abstract DataDao.TugasDao tugasDao();
    }
}
