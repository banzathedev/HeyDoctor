package com.proway.heydocapp.DAO

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.proway.heydocapp.database.AppDataBase
import com.proway.heydocapp.database.DAO.AppointmentsDAO
import com.proway.heydocapp.database.DAO.DoctorDAO
import com.proway.heydocapp.database.DAO.PatientDao
import com.proway.heydocapp.database.DAO.SpecialtiesDAO
import com.proway.heydocapp.model.AppointMentsTable
import com.proway.heydocapp.model.DoctorsTable
import com.proway.heydocapp.model.PatientsTable
import com.proway.heydocapp.model.SpecialtiesTable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class AppointmentsDaoTest {
    private lateinit var database: AppDataBase
    private lateinit var daoAppointments: AppointmentsDAO
    private lateinit var daoPatient: PatientDao
    private lateinit var daoSpeciality: SpecialtiesDAO
    private lateinit var daoDoctor: DoctorDAO

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDataBase::class.java
        ).allowMainThreadQueries().build()
        daoAppointments = database.getAppointmentsDao()
        daoPatient = database.getPatientDao()
        daoSpeciality = database.getSpecialtiesDao()
        daoDoctor = database.getDoctorsDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insert_schedule_returns_true() {
        val patient1 = PatientsTable(1, "Paciente1", 23, "male", 1)
        val patient2 = PatientsTable(2, "Paciente2", 23, "female", 2)
        daoPatient.insertPatient(patient1)
        daoPatient.insertPatient(patient2)

        val specialties1 = SpecialtiesTable(1, "Especialidade1")
        val specialties2 = SpecialtiesTable(2, "Especialidade2")
        daoSpeciality.insertSpecialties(specialties1)
        daoSpeciality.insertSpecialties(specialties2)

        val doc1 = DoctorsTable(1, "Medico1", specialties1.id)
        val doc2 = DoctorsTable(2, "Medico2", specialties2.id)
        daoDoctor.insertDoctor(doc1)
        daoDoctor.insertDoctor(doc2)

        val appointments1 = AppointMentsTable(1, patient1.id, doc1.id)
        val appointments2 = AppointMentsTable(2, patient2.id, doc2.id)
        daoAppointments.insertAppointment(appointments1)
        daoAppointments.insertAppointment(appointments2)

        val results = daoAppointments.getAppointments()
        assertThat(results).hasSize(2)
    }

    @Test
    fun get_by_medic_name() {
        val patient1 = PatientsTable(1, "Paciente1", 23, "male", 1)
        val patient2 = PatientsTable(2, "Paciente2", 23, "female", 2)
        daoPatient.insertPatient(patient1)
        daoPatient.insertPatient(patient2)

        val specialties1 = SpecialtiesTable(1, "Especialidade1")
        val specialties2 = SpecialtiesTable(2, "Especialidade2")
        daoSpeciality.insertSpecialties(specialties1)
        daoSpeciality.insertSpecialties(specialties2)

        val doc1 = DoctorsTable(1, "Medico1", specialties1.id)
        val doc2 = DoctorsTable(2, "Medico2", specialties2.id)
        daoDoctor.insertDoctor(doc1)
        daoDoctor.insertDoctor(doc2)

        val appointments1 = AppointMentsTable(1, patient1.id, doc1.id)
        val appointments2 = AppointMentsTable(2, patient2.id, doc2.id)
        daoAppointments.insertAppointment(appointments1)
        daoAppointments.insertAppointment(appointments2)

        val results = daoAppointments.fetchByDoctor("Medico")
        assertThat(results).hasSize(2)
    }

    @Test
    fun get_by_patient_name() {
        val patient1 = PatientsTable(1, "Paciente1", 23, "male", 1)
        val patient2 = PatientsTable(2, "Paciente2", 23, "female", 2)
        daoPatient.insertPatient(patient1)
        daoPatient.insertPatient(patient2)

        val specialties1 = SpecialtiesTable(1, "Especialidade1")
        val specialties2 = SpecialtiesTable(2, "Especialidade2")
        daoSpeciality.insertSpecialties(specialties1)
        daoSpeciality.insertSpecialties(specialties2)

        val doc1 = DoctorsTable(1, "Medico1", specialties1.id)
        val doc2 = DoctorsTable(2, "Medico2", specialties2.id)
        daoDoctor.insertDoctor(doc1)
        daoDoctor.insertDoctor(doc2)

        val appointments1 = AppointMentsTable(1, patient1.id, doc1.id)
        val appointments2 = AppointMentsTable(2, patient2.id, doc2.id)
        daoAppointments.insertAppointment(appointments1)
        daoAppointments.insertAppointment(appointments2)

        val results = daoAppointments.fetchByPatient("Paciente1")
        assertThat(results).hasSize(1)
    }

    @Test
    fun get_by_id() {
        val patient1 = PatientsTable(1, "Paciente1", 23, "male", 1)
        val patient2 = PatientsTable(2, "Paciente2", 23, "female", 2)
        daoPatient.insertPatient(patient1)
        daoPatient.insertPatient(patient2)

        val specialties1 = SpecialtiesTable(1, "Especialidade1")
        val specialties2 = SpecialtiesTable(2, "Especialidade2")
        daoSpeciality.insertSpecialties(specialties1)
        daoSpeciality.insertSpecialties(specialties2)

        val doc1 = DoctorsTable(1, "Medico1", specialties1.id)
        val doc2 = DoctorsTable(2, "Medico2", specialties2.id)
        daoDoctor.insertDoctor(doc1)
        daoDoctor.insertDoctor(doc2)

        val appointments1 = AppointMentsTable(1, patient1.id, doc1.id)
        val appointments2 = AppointMentsTable(2, patient2.id, doc2.id)
        daoAppointments.insertAppointment(appointments1)
        daoAppointments.insertAppointment(appointments2)

        val result = daoAppointments.fetchAppointments(1)
        assertThat(result.doctors?.name).isEqualTo(doc1.name)
        assertThat(result.patient.name).isEqualTo(patient1.name)
    }


    @Test
    fun delete() {
        val patient1 = PatientsTable(1, "Paciente1", 23, "male", 1)
        val patient2 = PatientsTable(2, "Paciente2", 23, "female", 2)
        daoPatient.insertPatient(patient1)
        daoPatient.insertPatient(patient2)

        val specialties1 = SpecialtiesTable(1, "Especialidade1")
        val specialties2 = SpecialtiesTable(2, "Especialidade2")
        daoSpeciality.insertSpecialties(specialties1)
        daoSpeciality.insertSpecialties(specialties2)

        val doc1 = DoctorsTable(1, "Medico1", specialties1.id)
        val doc2 = DoctorsTable(2, "Medico2", specialties2.id)
        daoDoctor.insertDoctor(doc1)
        daoDoctor.insertDoctor(doc2)

        val appointments1 = AppointMentsTable(1, patient1.id, doc1.id)
        val appointments2 = AppointMentsTable(2, patient2.id, doc2.id)
        daoAppointments.insertAppointment(appointments1)
        daoAppointments.insertAppointment(appointments2)


        daoAppointments.deleteAppointment(appointments1)

        val result = daoAppointments.getAppointments()
        assertThat(result).hasSize(1)
    }
}

