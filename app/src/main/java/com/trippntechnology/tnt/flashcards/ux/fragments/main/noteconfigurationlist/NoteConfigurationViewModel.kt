package com.trippntechnology.tnt.flashcards.ux.fragments.main.noteconfigurationlist

import com.trippntechnology.tnt.flashcards.database.NoteConfigurationRepository
import com.trippntechnology.tnt.flashcards.objects.noteconfiguration.NoteConfiguration
import com.trippntechnology.tnt.flashcards.util.livedata.SingleLiveEvent
import com.trippntechnology.tnt.flashcards.util.viewmodels.BaseViewModel
import com.vikingsen.inject.viewmodel.ViewModelInject

class NoteConfigurationViewModel @ViewModelInject constructor(noteConfigurationRepository: NoteConfigurationRepository) :
    BaseViewModel() {

    val noteConfigList = noteConfigurationRepository.getNoteConfigListLiveData()

    val loadConfig = SingleLiveEvent<Long>()
    val displayPacket = SingleLiveEvent<Long>()


    fun loadConfig(noteConfiguration: NoteConfiguration?){
        noteConfiguration?:return
        loadConfig.value = noteConfiguration.id
    }

    fun displayPacket(noteConfiguration: NoteConfiguration){
        displayPacket.value = noteConfiguration.id
    }

    fun newNoteConfig(){
        loadConfig.value = 0
    }

}