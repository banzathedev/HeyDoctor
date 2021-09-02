package com.proway.heydocapp.repositoy

import com.proway.heydocapp.database.AppDataBase
import com.proway.heydocapp.model.SpecialtiesTable
import javax.inject.Inject

class SpecialtiesRepository @Inject constructor(private val database: AppDataBase) {
    fun getSpecialties(callback: (List<SpecialtiesTable>?, String?) -> Unit){
        database.getSpecialtiesDao().getAllSpecialties().let { foundSpecialties ->
            if (foundSpecialties != null){
                callback(foundSpecialties, null)
            } else callback(null, "Nenhum Especialidade Encontrada")
        }
    }
    fun addSpecialties(specialties: SpecialtiesTable, callback: (Boolean) -> Unit){
        database.getSpecialtiesDao().insertSpecialties(specialties)
        callback(true)
    }
}