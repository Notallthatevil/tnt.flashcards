package com.trippntechnology.tnt.flashcards.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.trippntechnology.tnt.flashcards.objects.noteconfiguration.NoteConfiguration
import com.trippntechnology.tnt.flashcards.objects.noteconfiguration.NoteConfigurationDao


@Database(
    entities = [NoteConfiguration::class],
    version = 1,
    exportSchema = true

)
abstract class MainDatabase : RoomDatabase() {
    abstract val NoteConfigurationDao: NoteConfigurationDao







    companion object {
        const val DATABASE_NAME = "main.db"
    }

}