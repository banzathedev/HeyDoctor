package com.proway.heydocapp.database.DAO

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import com.proway.heydocapp.model.AppointMentsTable
import com.proway.heydocapp.model.AppointmentsPojo

@Dao
interface AppointmentsDAO {

    @Query("Select * from AppointMentsTable")
    fun getAppointments(): List<AppointmentsPojo>

    @Transaction
    @Query("Select * from AppointMentsTable where apt_id = :id")
    fun fetchAppointments(id: Int): AppointmentsPojo

    @Transaction
    @Query("Select * from AppointMentsTable inner join DoctorsTable on DoctorsTable.Doctors_id = doctorFK where DoctorsTable.Doctors_name like '%' || :name || '%'")
    fun fetchByDoctor(name: String): List<AppointmentsPojo>

    @Transaction
    @Query("Select * from AppointMentsTable inner join PatientsTable on PatientsTable.Patient_id = patientFK where PatientsTable.Patient_name like '%' || :name || '%'")
    fun fetchByPatient(name: String): List<AppointmentsPojo>

    @Transaction
    @Query("Select * from AppointMentsTable inner join PatientsTable on PatientsTable.Patient_id = patientFK where Patient_sex = :sex")
    fun fetchBySex(sex: String): List<AppointmentsPojo>

    @Insert(onConflict = ABORT)
    fun insertAppointment(appointments: AppointMentsTable)

    @Delete
    fun deleteAppointment(appointments: AppointMentsTable)

    @Update
    fun updateAppointments(appointments: AppointMentsTable)

}