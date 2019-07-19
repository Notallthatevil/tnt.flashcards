package com.trippntechnology.tnt.flashcards.ux.fragments.main.noteeditconfig

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trippntechnology.tnt.flashcards.database.NoteConfigurationRepository
import com.trippntechnology.tnt.flashcards.objects.note.Note
import com.trippntechnology.tnt.flashcards.objects.noteconfiguration.NoteConfiguration
import com.trippntechnology.tnt.flashcards.util.livedata.AbsentLiveData
import com.trippntechnology.tnt.flashcards.util.livedata.SingleLiveEvent
import com.trippntechnology.tnt.flashcards.util.viewmodels.BaseViewModel
import com.vikingsen.inject.viewmodel.ViewModelInject
import kotlinx.coroutines.launch

class NoteEditConfigViewModel @ViewModelInject constructor(private val noteConfigurationRepository: NoteConfigurationRepository) :
    BaseViewModel() {

    private val noteConfigId = MutableLiveData<Long>()
    val noteConfig: LiveData<NoteConfiguration>
    val viewModelEvent = MutableLiveData<Int>()


    init {
        noteConfig = AbsentLiveData.switchMap(noteConfigId) {
            noteConfigurationRepository.getNoteConfigById(it)
        }
    }

    fun setConfigId(configId: Long) {
        noteConfigId.value = configId
    }

    fun saveNoteConfig(id: Long, name: String, noteList: List<Note>) {
        val noteConfig = NoteConfiguration(id, name, noteList)
        launch {
            noteConfigurationRepository.saveNoteConfig(noteConfig)
            viewModelEvent.postValue(EVENT_FINISHED)
        }
    }

    fun saveConfigButton() {
        viewModelEvent.postValue(EVENT_SAVE)
    }

    fun cancelConfigButton() {
        viewModelEvent.postValue(EVENT_FINISHED)
    }

    companion object {
        const val EVENT_SAVE = 100
        const val EVENT_FINISHED = 101

    }
}