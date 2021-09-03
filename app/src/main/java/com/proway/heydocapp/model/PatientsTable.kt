package com.proway.heydocapp.model

import androidx.room.*
import java.io.Serializable

@Entity
data class PatientsTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Patient_id")
    var id: Int = 0,
    @ColumnInfo(name = "Patient_name")
    val name: String,
    @ColumnInfo(name = "Patient_age")
    val age: Int,
    @ColumnInfo(name = "Patient_sex")
    val sex: String,

    @ColumnInfo(name = "doctorIdFk")
    var doctorIdFk : Int?
)

data class PatientsWithDoctors(
    @Embedded
    val patient: PatientsTable?,
    @Relation(
        parentColumn = "doctorIdFk",
        entityColumn = "Doctors_id"
    )
    val doctor: DoctorsTable?

): Serializable