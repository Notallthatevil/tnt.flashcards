package com.trippntechnology.tnt.flashcards.ux.activities.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trippntechnology.tnt.flashcards.database.NoteConfigurationRepository
import com.trippntechnology.tnt.flashcards.objects.noteconfiguration.NoteConfiguration
import com.trippntechnology.tnt.flashcards.objects.note.Note
import com.trippntechnology.tnt.flashcards.util.livedata.SingleLiveEvent
import com.trippntechnology.tnt.flashcards.util.viewmodels.BaseViewModel
import com.vikingsen.inject.viewmodel.ViewModelInject
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val noteConfigurationRepository: NoteConfigurationRepository) :
    BaseViewModel() {

    val mainViewModelEvent = MutableLiveData<Int>()
    val enabledNotesList = retrieveNoteConfigs()
    val loadConfig = SingleLiveEvent<Long>()

    var loadedConfig: NoteConfiguration? = null

    private fun retrieveNoteConfigs(): LiveData<List<NoteConfiguration>> {
        return noteConfigurationRepository.getNoteConfigListLiveData()
    }

    fun newEnabledNotesConfig() {
        loadedConfig = null
        mainViewModelEvent.postValue(EVENT_NEW_ENABLED_NOTES_CONFIG)
    }

    fun showFlashCards(noteConfigurationConfig: NoteConfiguration) {

    }

    fun loadConfig(noteConfig: NoteConfiguration) {
        loadConfig.value = noteConfig.id
    }

    fun saveNoteConfig(id: Long, name: String, noteList: List<Note>) {
        val noteConfig = NoteConfiguration(id, name, noteList)
        launch {
            noteConfigurationRepository.saveNoteConfig(noteConfig)
        }
        mainViewModelEvent.postValue(EVENT_CONFIG_SAVED)
    }


    fun saveConfigButton() {
        mainViewModelEvent.postValue(EVENT_SAVE_CONFIG)
    }

    fun cancelConfigButton() {
        mainViewModelEvent.postValue(EVENT_CANCEL_CONFIG)
    }

    companion object {
        const val EVENT_NEW_ENABLED_NOTES_CONFIG = 100
        const val EVENT_SAVE_CONFIG = 110
        const val EVENT_CANCEL_CONFIG = 111
        const val EVENT_CONFIG_SAVED = 120
        const val EVENT_LOAD_CONFIG = 200

    }

}