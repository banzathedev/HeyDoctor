package com.proway.heydocapp.database.DAO

import androidx.room.*

import com.proway.heydocapp.model.PatientsTable
import com.proway.heydocapp.model.PatientsWithDoctors

@Dao
interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPatient(patient: PatientsTable)

    fun inserPatient(patientWithDoctor: PatientsWithDoctors) {
        if (patientWithDoctor.doctor != null) {
            patientWithDoctor.patient?.doctorIdFk = patientWithDoctor.doctor.id
            patientWithDoctor?.let { inserPatient(it) }
        } else {
            patientWithDoctor?.let { inserPatient(it) }
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


