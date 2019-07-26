package com.trippntechnology.tnt.flashcards.database

import androidx.lifecycle.LiveData
import com.trippntechnology.tnt.flashcards.objects.noteconfiguration.NoteConfiguration
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteConfigurationRepository @Inject constructor(private val mainDatabaseWrapper: MainDatabaseWrapper) {

    private fun mainDatabase() = mainDatabaseWrapper.getDatabase()

    private fun noteConfigDao() = mainDatabase().NoteConfigurationDao

    fun getNoteConfigListLiveData() = noteConfigDao().selectAllLiveData()
    fun getNoteConfigById(id: Long) = noteConfigDao().getById(id)
    fun getNoteConfigByIdLiveData(id: Long) = noteConfigDao().getByIdLiveData(id)
    fun getNoteConfigList() = noteConfigDao().selectAll()
    fun getNoteConfigCount() = noteConfigDao().count()
    fun deleteAllNoteConfig() = noteConfigDao().deleteAll()
    fun deleteNoteConfigById(id: Long) = noteConfigDao().deleteById(id)

    fun saveNoteConfig(noteConfiguration: NoteConfiguration) {
        if (noteConfiguration.id <= 0) {
            val newId = noteConfigDao().insert(noteConfiguration)
            noteConfiguration.id = newId
        } else {
            noteConfigDao().update(noteConfiguration)
        }
    }
}

