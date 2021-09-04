package com.proway.heydocapp.model

import androidx.room.*
import java.io.Serializable

@Entity
data class AppointMentsTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "apt_id")
    var id: Int = 0,
    val patientFK: Int,
    val doctorFK: Int
)

data class AppointmentsPojo(
    @Embedded
    val appointments: AppointMentsTable,

    @Relation(
        parentColumn = "patientFK",
        entityColumn = "Patient_id"
    )
    val patient: PatientsTable,

    @Relation(
        parentColumn = "doctorFK",
        entityColumn = "Doctors_id"
    )
    val doctors: DoctorsTable
): Serializable
