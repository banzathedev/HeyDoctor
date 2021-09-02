package com.proway.heydocapp.DAO

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.proway.heydocapp.database.AppDataBase
import com.proway.heydocapp.database.DAO.DoctorDAO
import com.proway.heydocapp.model.DoctorsTable
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

    @Before
    fun setup(){
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDataBase::class.java
        ).allowMainThreadQueries()
            .build()
        dao = dataBase.getDoctorsDao()
    }
    @After
    fun teardown(){
        dataBase.close()
    }
    @Test
    fun test_insert_must_return_true(){
        val docModel = DoctorsTable(0,"Doc testTUdo", 0)
        dao.insertDoctor(docModel)
        val docs = dao.getAllDoctors()

        assertThat(docs).contains(docModel)
    }
}