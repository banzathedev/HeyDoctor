package com.proway.heydocapp.DAO

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.proway.heydocapp.database.AppDataBase

import com.proway.heydocapp.database.DAO.PatientDao
import com.proway.heydocapp.database.DAO.SpecialtiesDAO
import com.proway.heydocapp.model.PatientsTable
import com.proway.heydocapp.model.SpecialtiesTable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@SmallTest
class SpecialtiesDaoTest {
    private lateinit var dataBase: AppDataBase
    private lateinit var dao: SpecialtiesDAO

    @Before
    fun setup(){
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDataBase::class.java
        ).allowMainThreadQueries()
            .build()
        dao = dataBase.getSpecialtiesDao()
    }
    @After
    fun teardown(){
        dataBase.close()
    }
    @Test
    fun test_insert_must_return_true(){
        val specialties = SpecialtiesTable(1,"oCUlista")
        dao.insertSpecialties(specialties)
        val spec = dao.getAllSpecialties()

        assertThat(spec).contains(specialties)
    }

}