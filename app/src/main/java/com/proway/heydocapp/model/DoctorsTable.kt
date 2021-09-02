package com.proway.heydocapp.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.PrimaryKey
import androidx.room.Relation

data class DoctorsTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "Doctors_id")
    val id : Int,
    @ColumnInfo(name= "Doctors_name")
    val name: String,

    val specialtiesTableFK: Int
)


data class DoctorWithSpecialties(
    @Embedded
    val doctor: DoctorsTable?,
    @Relation(
        parentColumn = "specialtiesTableFK",
        entityColumn = "Specialties_id"
    )
    val specialties: SpecialtiesTable?

)
 /* carai acho que consegui */
