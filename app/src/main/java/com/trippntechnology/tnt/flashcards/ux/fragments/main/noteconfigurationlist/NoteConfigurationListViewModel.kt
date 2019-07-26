package com.trippntechnology.tnt.flashcards.ux.fragments.main.noteconfigurationlist

import androidx.lifecycle.MutableLiveData
import com.trippntechnology.tnt.flashcards.database.NoteConfigurationRepository
import com.trippntechnology.tnt.flashcards.objects.noteconfiguration.NoteConfiguration
import com.trippntechnology.tnt.flashcards.util.livedata.SingleLiveEvent
import com.trippntechnology.tnt.flashcards.util.viewmodels.BaseViewModel
import com.vikingsen.inject.viewmodel.ViewModelInject

class NoteConfigurationListViewModel @ViewModelInject constructor(noteConfigurationRepository: NoteConfigurationRepository) :
    BaseViewModel() {

    val noteConfigList = noteConfigurationRepository.getNoteConfigListLiveData()

    val loadConfig = SingleLiveEvent<Long>()
    val openPacket = SingleLiveEvent<Long>()

    fun loadConfig(noteConfiguration: NoteConfiguration?): Boolean {
        if (noteConfiguration != null) {
            loadConfig.value = noteConfiguration.id
        }
        return true
    }

    fun displayPacket(noteConfiguration: NoteConfiguration) {
        openPacket.value = noteConfiguration.id
    }

    fun newNoteConfig() {
        loadConfig.value = 0
    }
}