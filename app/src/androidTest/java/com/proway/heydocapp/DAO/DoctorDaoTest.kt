package com.proway.heydocapp.DAO

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.proway.heydocapp.database.AppDataBase
import com.proway.heydocapp.database.DAO.DoctorDAO
import com.proway.heydocapp.database.DAO.SpecialtiesDAO
import com.proway.heydocapp.model.DoctorWithSpecialties
import com.proway.heydocapp.model.DoctorsTable
import com.proway.heydocapp.model.SpecialtiesTable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@SmallTest
class DoctorDaoTest {
    private lateinit var dataBase: AppDataBase
    private lateinit var dao: DoctorDAO
    private lateinit var daoSpecialties: SpecialtiesDAO

    @Before
    fun setup(){
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDataBase::class.java
        ).allowMainThreadQueries()
            .build()
        dao = dataBase.getDoctorsDao()
        daoSpecialties = dataBase.getSpecialtiesDao()
    }
    @After
    fun teardown(){
        dataBase.close()
    }
    @Test
    fun test_insert_must_return_true(){
        val docModel = DoctorsTable(1,"Doc testTUdo", 0)
        dao.insertDoctor(docModel)
        val docs = dao.getAllDoctors()

        assertThat(docs).contains(docModel)
    }
    @Test
    fun test_insertDocWithSpecialties_must_return_true(){
        val docModel = DoctorsTable(1,"Doc testTUdo", 1)
        dao.insertDoctor(docModel)
        val docs = dao.getAllDoctors()

        assertThat(docs).contains(docModel)
    }
    @Test
    fun test_getDocById_must_return_true(){
        val docModel = DoctorsTable(1,"Doc testTUdo", 1)
        dao.insertDoctor(docModel)
        val docs = dao.getDocById(1)

        assertThat(docs).isEqualTo(docModel)
    }
    @Test
    fun test_getDoctorsWithCategories_must_return_true(){
        val docModel = DoctorsTable(1,"Doc testTUdo", 1)
        val specialties = SpecialtiesTable(1, "o CUlista")
        daoSpecialties.insertSpecialties(specialties)
        val docComSp = DoctorWithSpecialties(docModel, specialties)
        dao.insertDoctor(docComSp)
        val docs = dao.getDocWithSp()

        assertThat(docs).contains(docComSp)
    }
}