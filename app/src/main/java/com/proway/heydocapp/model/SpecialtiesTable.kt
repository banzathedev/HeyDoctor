package com.proway.heydocapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class SpecialtiesTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Specialties_id")
    var id: Int = 0,
    @ColumnInfo(name = "Specialties_name")
    val name: String,

    ): Serializable