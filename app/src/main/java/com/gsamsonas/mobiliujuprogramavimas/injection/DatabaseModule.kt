package com.gsamsonas.mobiliujuprogramavimas.injection

import android.content.Context
import androidx.room.Room
import com.gsamsonas.mobiliujuprogramavimas.data.room.DefaultDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDefaultDatabase(@ApplicationContext app: Context): DefaultDatabase {
        return Room.databaseBuilder(
            app, DefaultDatabase::class.java, "default_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMapMarkerDao(database: DefaultDatabase) = database.mapMarkerDao()
}