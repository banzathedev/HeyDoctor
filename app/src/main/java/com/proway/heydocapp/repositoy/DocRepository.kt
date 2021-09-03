package com.proway.heydocapp.repositoy

import com.proway.heydocapp.database.AppDataBase
import com.proway.heydocapp.model.DoctorWithSpecialties
import com.proway.heydocapp.model.DoctorsTable

import javax.inject.Inject

class DocRepository @Inject constructor(private val database: AppDataBase) {
    fun getDoctors(callback: (List<DoctorWithSpecialties>?, String?) -> Unit) {
        database.getDoctorsDao().getDocWithSp().let { foundDoctors ->
            if (foundDoctors != null) {
                callback(foundDoctors, null)
            } else callback(null, "Nenhum Doutor Encontrado")

        }
    }

    fun addDoctor(doctor: DoctorWithSpecialties, callback: (Boolean) -> Unit) {
        database.getDoctorsDao().insertDoctor(doctor)
        callback(true)
    }

    fun addDoctorWithSpecialties(doctor: DoctorWithSpecialties, callback: (Boolean) -> Unit) {
        database.getDoctorsDao().insertDoctor(doctor)
        callback(true)
    }
    fun delete(doctor: DoctorWithSpecialties, callback: (Boolean) -> Unit){
        database.getDoctorsDao().delete(doctor)
        callback(true)
    }
}