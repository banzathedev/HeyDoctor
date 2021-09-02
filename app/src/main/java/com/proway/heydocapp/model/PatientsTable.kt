package com.proway.heydocapp.model

import androidx.room.*

@Entity
data class PatientsTable(
    @PrimaryKey
    @ColumnInfo(name = "Patient_id")
    val id: Int,
    @ColumnInfo(name = "Patient_name")
    val name: String,
    @ColumnInfo(name = "Patient_age")
    val age: Int,
    @ColumnInfo(name = "Patient_sex")
    val sex: String,

    @ColumnInfo(name = "doctorIdFk")
    var doctorIdFk : Int
)

data class PatientsWithDoctors(
    @Embedded
    val patient: PatientsTable?,
    @Relation(
        parentColumn = "doctorIdFk",
        entityColumn = "Doctors_id"
    )
    val doctor: DoctorsTable?

)