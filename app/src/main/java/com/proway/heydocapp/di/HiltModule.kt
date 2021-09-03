package com.proway.heydocapp.di

import android.content.Context
import com.proway.heydocapp.database.AppDataBase
import com.proway.heydocapp.repositoy.AppointmentsRepository
import com.proway.heydocapp.repositoy.DocRepository
import com.proway.heydocapp.repositoy.PatientsRepository
import com.proway.heydocapp.repositoy.SpecialtiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides  /* injetcs database aready with the context. */
    fun getDb(@ApplicationContext context: Context): AppDataBase{
        return AppDataBase.getDatabase(context)
    }

                /* This guys privides the repositories */
    @Provides
    fun getSpecialtyRepository(dataBase: AppDataBase): SpecialtiesRepository = SpecialtiesRepository(dataBase)

    @Provides
    fun getDoctorRepository(dataBase: AppDataBase): DocRepository = DocRepository(dataBase)

    @Provides
    fun getPacientRepository(dataBase: AppDataBase): PatientsRepository = PatientsRepository(dataBase)

    @Provides
    fun getAppointmentsRepository(dataBase: AppDataBase): AppointmentsRepository = AppointmentsRepository(dataBase)

}

