package com.trippntechnology.tnt.flashcards.ux.fragments.main.noteeditconfig

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.trippntechnology.tnt.flashcards.database.NoteConfigurationRepository
import com.trippntechnology.tnt.flashcards.objects.noteconfiguration.NoteConfiguration
import com.trippntechnology.tnt.flashcards.util.livedata.AbsentLiveData
import com.trippntechnology.tnt.flashcards.util.viewmodels.BaseViewModel
import com.vikingsen.inject.viewmodel.ViewModelInject

class NoteEditConfigViewModel @ViewModelInject constructor(private val noteConfigurationRepository: NoteConfigurationRepository) :
    BaseViewModel() {

    val noteConfigId = MutableLiveData<Long>()
    val noteConfig :LiveData<NoteConfiguration>


    init {
        noteConfig = AbsentLiveData.switchMap(noteConfigId) {
            noteConfigurationRepository.getNoteConfigById(it)
        }
    }

    fun setConfigId(configId: Long) {
        noteConfigId.value = configId
    }

    fun saveConfigButton(){

    }

    fun cancelConfigButton(){

    }
}