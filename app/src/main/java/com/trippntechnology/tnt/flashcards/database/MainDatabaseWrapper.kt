package com.trippntechnology.tnt.flashcards.database

import android.app.Application
import androidx.room.Room
import org.dbtools.android.room.CloseableDatabaseWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class MainDatabaseWrapper @Inject constructor(application: Application) :
    CloseableDatabaseWrapper<MainDatabase>(application) {

    override fun createDatabase(): MainDatabase {
        return Room.databaseBuilder(
            application,
            MainDatabase::class.java,
            MainDatabase.DATABASE_NAME
        ).build()
    }
}