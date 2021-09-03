package com.proway.heydocapp.database.DAO

import androidx.room.*

import com.proway.heydocapp.model.PatientsTable
import com.proway.heydocapp.model.PatientsWithDoctors

@Dao
interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPatient(patient: PatientsTable)

    fun insertPatient(patientWithDoctor: PatientsWithDoctors) {
        if (patientWithDoctor.doctor != null) {
            patientWithDoctor.patient?.doctorIdFk = patientWithDoctor.doctor.id
            patientWithDoctor.patient?.let { insertPatient(it) }
        } else {
            patientWithDoctor.patient?.let { insertPatient(it) }
        }
    }

    @Query("SELECT * FROM PatientsTable WHERE Patient_id = :id")
    fun getPatientById(id: Int): PatientsTable

    @Query("SELECT * FROM PatientsTable")
    fun getPatients(): List<PatientsTable>

    @Query("SELECT * FROM PatientsTable")
    fun getPatientsWithDoctor(): List<PatientsWithDoctors>

    @Delete
    fun deleteDoctor(Patient: PatientsTable)

}


