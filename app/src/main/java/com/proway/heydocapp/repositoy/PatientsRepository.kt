package com.proway.heydocapp.repositoy

import com.proway.heydocapp.database.AppDataBase
import com.proway.heydocapp.model.PatientsTable
import com.proway.heydocapp.model.PatientsWithDoctors
import javax.inject.Inject

class PatientsRepository @Inject constructor(private val database: AppDataBase) {
    fun getPatients(callback: (List<PatientsWithDoctors>?, String?) -> Unit) {
        database.getPatientDao().getPatientsWithDoctor().let { foundPatients ->
            if (foundPatients != null) {
                callback(foundPatients, null)
            } else callback(null, "Nenhum Paciente Encontrado")

        }
    }
    fun addPatient(patient: PatientsTable, callback: (Boolean) -> Unit) {
        database.getPatientDao().insertPatient(patient)
        callback(true)
    }

    fun addPatientsWithDoctors(patient: PatientsWithDoctors, callback: (Boolean) -> Unit) {
        database.getPatientDao().insertPatient(patient)
        callback(true)
    }

    fun deletePatient(patient: PatientsTable, callback: (Boolean) -> Unit){
        database.getPatientDao().deletePatient(patient)
        callback(true)
    }
}
