package com.example.belgiclassroom;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.example.belgiclassroom.DATA.AppDatabase;
import com.example.belgiclassroom.DATA.DataDao;

public class BelgiApplication extends Application {

    public static final int USER_AS_TEACHER = 0;
    public static final int USER_AS_STUDENT = 1;

    private static int USER_TYPE = 0x00;

    private static DataDao.KelasDao kelasDao;
    private static DataDao.TugasDao tugasDao;

    @Override
    public void onCreate() {
        super.onCreate();

        AppDatabase.DataKelas dataKelas = Room.databaseBuilder(this,
                AppDatabase.DataKelas.class, "kelas-data").allowMainThreadQueries().build();

        AppDatabase.DataTugas dataTugas = Room.databaseBuilder(this,
                AppDatabase.DataTugas.class, "tugas-data").allowMainThreadQueries().build();

        kelasDao = dataKelas.kelasDao();
        tugasDao = dataTugas.tugasDao();
    }

    public static DataDao.KelasDao getKelasDao(){
        return kelasDao;
    }

    public static DataDao.TugasDao getTugasDao(){
        return tugasDao;
    }

    public static void setUserType(int userType){
        USER_TYPE = userType;
    }

    public static int getUserType(){
        return USER_TYPE;
    }

}
