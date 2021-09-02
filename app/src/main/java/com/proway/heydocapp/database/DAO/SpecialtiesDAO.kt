package com.proway.heydocapp.database.DAO

import androidx.room.*

import com.proway.heydocapp.model.SpecialtiesTable

@Dao
interface SpecialtiesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecialties(specialties: SpecialtiesTable)

    @Query("SELECT * FROM SpecialtiesTable")
    fun getAllSpecialties(): List<SpecialtiesTable>

    @Delete
    fun deleteSpecialties(specialties: SpecialtiesTable)
}