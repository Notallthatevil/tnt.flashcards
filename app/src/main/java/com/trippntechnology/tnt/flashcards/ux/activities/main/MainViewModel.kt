package com.trippntechnology.tnt.flashcards.ux.activities.main

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trippntechnology.tnt.flashcards.database.EnabledNotesRepository
import com.trippntechnology.tnt.flashcards.objects.enablednotes.EnabledNotes
import com.trippntechnology.tnt.flashcards.objects.note.Note
import com.trippntechnology.tnt.flashcards.util.viewmodels.BaseViewModel
import com.vikingsen.inject.viewmodel.ViewModelInject
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val enabledNotesRepository: EnabledNotesRepository) :
    BaseViewModel() {

    val mainViewModelEvent = MutableLiveData<Int>()
    val enabledNotesList = retrieveNoteConfigs()

    var loadedConfig:EnabledNotes? = null

    private fun retrieveNoteConfigs(): LiveData<List<EnabledNotes>> {
        return enabledNotesRepository.getEnabledNotesListLiveData()
    }

    fun newEnabledNotesConfig() {
        loadedConfig = null
        mainViewModelEvent.postValue(EVENT_NEW_ENABLED_NOTES_CONFIG)
    }

    fun showFlashCards(enabledNotesConfig: EnabledNotes){

    }

    fun loadConfig(view: View, enabledNotesConfig: EnabledNotes) {
    }

    fun saveNoteConfig(id:Long,name:String,noteList:List<Note>){
        val enabledNotes = EnabledNotes(id,name,noteList)
        launch {
            enabledNotesRepository.saveEnabledNotesConfig(enabledNotes)
        }
        mainViewModelEvent.postValue(EVENT_CONFIG_SAVED)
    }


    fun saveConfigButton() {
        mainViewModelEvent.postValue(EVENT_SAVE_CONFIG)
    }

    fun cancelConfigButton() {

    }

    companion object {
        const val EVENT_NEW_ENABLED_NOTES_CONFIG = 100
        const val EVENT_SAVE_CONFIG = 110
        const val EVENT_CONFIG_SAVED = 120

    }

}