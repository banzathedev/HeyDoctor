package com.proway.heydocapp.database.DAO

import androidx.room.*
import com.proway.heydocapp.model.DoctorWithSpecialties
import com.proway.heydocapp.model.DoctorsTable

import com.proway.heydocapp.model.PatientsTable

@Dao
interface DoctorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctor(doctor: DoctorsTable)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctorWithSp(docPlus: DoctorWithSpecialties)

    @Query("SELECT * FROM DoctorsTable WHERE Doctors_id = :id")
    fun getDocById(id: Int): PatientsTable

    @Query("SELECT * FROM DoctorsTable")
    fun getAllDoctors(id: Int): List<DoctorsTable>

    @Query("SELECT * FROM DoctorsTable")
    fun getDocWithSp(): List<DoctorWithSpecialties> //for now this guy its just for test

    @Delete
    fun delete(doctor: DoctorsTable)

}