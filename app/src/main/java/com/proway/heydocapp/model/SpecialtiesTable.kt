package com.proway.heydocapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SpecialtiesTable(
    @PrimaryKey
    @ColumnInfo(name = "Specialties_id")
    val id: Int,
    @ColumnInfo(name = "Specialties_name")
    val name: String,

    )