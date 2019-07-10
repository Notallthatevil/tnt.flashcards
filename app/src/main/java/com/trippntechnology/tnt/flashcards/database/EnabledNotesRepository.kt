package com.trippntechnology.tnt.flashcards.database

import com.trippntechnology.tnt.flashcards.objects.enablednotes.EnabledNotes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EnabledNotesRepository @Inject constructor(private val mainDatabaseWrapper: MainDatabaseWrapper) {

    private fun mainDatabase() = mainDatabaseWrapper.getDatabase()

    private fun enabledNotesDao() = mainDatabase().EnabledNotesDao

    fun getEnabledNotesListLiveData() = enabledNotesDao().selectAllLiveData()
    fun getEnabledNotesList() = enabledNotesDao().selectAll()
    fun getEnabledNotesById(id: Long) = enabledNotesDao().getById(id)
    fun getEnabledNotesCount() = enabledNotesDao().count()
    fun deleteAllEnabledNotes() = enabledNotesDao().deleteAll()
    fun deleteEnabledNotesById(id: Long) = enabledNotesDao().deleteById(id)

    fun saveEnabledNotesConfig(enabledNotes: EnabledNotes) {
        if (enabledNotes.id <= 0) {
            val newId = enabledNotesDao().insert(enabledNotes)
            enabledNotes.id = newId
        } else {
            enabledNotesDao().update(enabledNotes)
        }
    }
}

