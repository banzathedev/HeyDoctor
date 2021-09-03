package com.proway.heydocapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.proway.heydocapp.database.DAO.DoctorDAO
import com.proway.heydocapp.database.DAO.PatientDao
import com.proway.heydocapp.database.DAO.SpecialtiesDAO
import com.proway.heydocapp.model.DoctorsTable

import com.proway.heydocapp.model.PatientsTable
import com.proway.heydocapp.model.SpecialtiesTable

@Database(
    entities = [DoctorsTable::class, PatientsTable::class, SpecialtiesTable::class],
    version = 2
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getPatientDao(): PatientDao
    abstract fun getDoctorsDao(): DoctorDAO
    abstract fun getSpecialtiesDao(): SpecialtiesDAO

    companion object {
        fun getDatabase(context: Context): AppDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "heyDoc_app_db"
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
    }

}


