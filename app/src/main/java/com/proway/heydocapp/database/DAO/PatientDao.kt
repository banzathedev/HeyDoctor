package com.proway.heydocapp.database.DAO

import android.provider.SyncStateContract.Helpers.insert
import androidx.room.*
import com.proway.heydocapp.model.PatientWithDoctorTable
import com.proway.heydocapp.model.PatientsTable

@Dao
interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPatient(patient: PatientsTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPatientWithDoctor(patientWithDoctor: PatientWithDoctorTable)

    @Query("SELECT * FROM PatientsTable WHERE Patient_id = :id")
    fun getPatientById(id: Int): PatientsTable

    @Query("SELECT * FROM PatientsTable")
    fun getPatients(): List<PatientsTable>

    @Query("SELECT * FROM PatientsTable")
    fun getPatientsWithDoctor(): List<PatientWithDoctorTable>

    @Delete
    fun deletePatient(Patient: PatientsTable)


}


