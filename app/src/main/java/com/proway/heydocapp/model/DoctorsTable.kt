package com.proway.heydocapp.model

import androidx.room.*

@Entity
data class DoctorsTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "Doctors_id")
    var id : Int = 0,
    @ColumnInfo(name= "Doctors_name")
    val name: String,


    var specialtiesTableFK: Int
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
