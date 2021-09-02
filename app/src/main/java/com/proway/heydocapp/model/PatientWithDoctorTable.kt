package com.proway.heydocapp.model

import androidx.room.ColumnInfo

import androidx.room.Relation

data class PatientWithDoctorTable(

    @Relation(
       parentColumn = "Patient_idFk",
       entityColumn = "Patient_id"
   )
    @ColumnInfo(name = "Patient_idFk")
    val patientIdFk: Int,

    @Relation(
        parentColumn = "Doctor_idFk",
        entityColumn = "Doctors_id"
    )
    @ColumnInfo(name = "Doctor_idFk")
    val doctorIdFk: Int,

)
