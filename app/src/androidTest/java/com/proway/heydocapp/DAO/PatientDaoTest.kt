package com.proway.heydocapp.DAO

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.proway.heydocapp.database.AppDataBase

import com.proway.heydocapp.database.DAO.PatientDao
import com.proway.heydocapp.model.DoctorsTable
import com.proway.heydocapp.model.PatientsTable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@SmallTest
class PatientDaoTest {
    private lateinit var dataBase: AppDataBase
    private lateinit var dao: PatientDao

    @Before
    fun setup(){
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDataBase::class.java
        ).allowMainThreadQueries()
            .build()
        dao = dataBase.getPatientDao()
    }
    @After
    fun teardown(){
        dataBase.close()
    }
    @Test
    fun test_insert_must_return_true(){
        val patientModel = PatientsTable(1,"Paciente testTUdo", 20, "male", 0)
        dao.insertPatient(patientModel)
        val pat = dao.getPatients()

        assertThat(pat).contains(patientModel)
    }
    @Test
    fun test_insertDocWithSpecialties_must_return_true(){
        val patientMode = PatientsTable(1,"Doc testTUdo", 1, "male", 0)
        dao.insertPatient(patientMode)
        val pat = dao.getPatients()

        assertThat(pat).contains(patientMode)
    }
    @Test
    fun test_getPatientById_must_return_true(){
        val patientMode = PatientsTable(1,"Doc testTUdo", 1, "female", 0)
        dao.insertPatient(patientMode)
        val pat = dao.getPatientById(1)

        assertThat(patientMode).isEqualTo(pat)
    }
    @Test
    fun test_getPatientsWithDoctors_must_return_true(){
        val patientMode = PatientsTable(1,"Doc testTUdo", 1, "male", 0)
        dao.insertPatient(patientMode)
        val pat = dao.getPatientsWithDoctor()

        assertThat(pat).contains(patientMode)
    }
}