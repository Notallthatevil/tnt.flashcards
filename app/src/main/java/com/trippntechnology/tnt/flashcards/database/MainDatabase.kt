package com.trippntechnology.tnt.flashcards.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.trippntechnology.tnt.flashcards.objects.enablednotes.EnabledNotes
import com.trippntechnology.tnt.flashcards.objects.enablednotes.EnabledNotesDao


@Database(
    entities = [EnabledNotes::class],
    version = 1
)
abstract class MainDatabase : RoomDatabase() {
    abstract val EnabledNotesDao: EnabledNotesDao







    companion object {
        const val DATABASE_NAME = "main.db"
    }

}