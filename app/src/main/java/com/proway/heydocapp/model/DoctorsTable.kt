package com.proway.heydocapp.model

import androidx.room.*
import java.io.Serializable

@Entity
data class DoctorsTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Doctors_id")
    var id: Int = 0,
    @ColumnInfo(name = "Doctors_name")
    val name: String,


    var specialtiesTableFK: Int
) : Serializable {
    override fun toString(): String {
        return name
    }
}


data class DoctorWithSpecialties(
    @Embedded
    val doctor: DoctorsTable?,
    @Relation(
        parentColumn = "specialtiesTableFK",
        entityColumn = "Specialties_id"
    )
    val specialties: SpecialtiesTable?

) : Serializable {
    override fun toString(): String {
        return doctor!!.name

    }
}
/* carai acho que consegui */
