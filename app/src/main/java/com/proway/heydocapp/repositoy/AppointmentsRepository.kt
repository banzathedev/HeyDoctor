package com.proway.heydocapp.repositoy

import com.proway.heydocapp.database.AppDataBase
import com.proway.heydocapp.model.AppointMentsTable
import com.proway.heydocapp.model.AppointmentsPojo
import javax.inject.Inject


class AppointmentsRepository @Inject constructor(private val database: AppDataBase) {

    fun addAppointment(appointment: AppointMentsTable, callback: (Boolean) -> Unit) {
        database.getAppointmentsDao().insertAppointment(appointment)
        callback(true)
    }

    fun getAppointments(): List<AppointmentsPojo> {
        database.getAppointmentsDao().getAppointments().let {
            return it
        }
    }

    fun fetchAppointmentsById(id: Int): AppointmentsPojo {
        database.getAppointmentsDao().fetchAppointments(id).let {
            return it
        }

    }

    fun fetchAppointmentsByDoc(name: String): List<AppointmentsPojo> {
        database.getAppointmentsDao().fetchByDoctor(name).let {
            return it
        }
    }

    fun fetchAppointmentsByPatient(name: String): List<AppointmentsPojo> {
        database.getAppointmentsDao().fetchByPatient(name).let {
            return it
        }
    }

    fun delete(appointment: AppointMentsTable) {
        database.getAppointmentsDao().deleteAppointment(appointment)
    }

    fun update(appointment: AppointMentsTable) {
        database.getAppointmentsDao().updateAppointments(appointment)
    }

}