package com.proway.heydocapp.di

import android.content.Context
import com.proway.heydocapp.database.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides  //injetcs database aready with the context.
    fun getDb(@ApplicationContext context: Context): AppDataBase{
        return AppDataBase.getDatabase(context)
    }
}

