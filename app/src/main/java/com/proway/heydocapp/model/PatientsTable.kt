package com.proway.heydocapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
)