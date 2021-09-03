package com.proway.heydocapp.database.DAO

import androidx.room.*
import com.proway.heydocapp.model.DoctorWithSpecialties
import com.proway.heydocapp.model.DoctorsTable

import com.proway.heydocapp.model.PatientsTable

@Dao
interface DoctorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctor(doctor: DoctorsTable)

    fun insertDoctor(docWithSpecialties: DoctorWithSpecialties){
        if(docWithSpecialties.specialties != null){
            docWithSpecialties.doctor?.specialtiesTableFK = docWithSpecialties.specialties.id
            docWithSpecialties.doctor?.let { insertDoctor(it) }
        } else {
            docWithSpecialties.doctor?.let {insertDoctor(it)}
        }
    }

    @Query("SELECT * FROM DoctorsTable WHERE Doctors_id = :id")
    fun getDocById(id: Int): DoctorsTable

    @Query("SELECT * FROM DoctorsTable")
    fun getAllDoctors(): List<DoctorsTable>

    @Query("SELECT * FROM DoctorsTable")
    fun getDocWithSp(): List<DoctorWithSpecialties> //for now this guy its just for test

    @Delete
    fun delete(doctor: DoctorWithSpecialties)

}