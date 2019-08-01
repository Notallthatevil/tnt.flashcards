package com.trippntechnology.tnt.flashcards.ux.fragments.main.noteeditconfig

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.trippntechnology.tnt.androidbaseutils.ux.livedata.AbsentLiveData
import com.trippntechnology.tnt.androidbaseutils.ux.livedata.SingleLiveEvent
import com.trippntechnology.tnt.androidbaseutils.ux.viewmodels.BaseViewModel
import com.trippntechnology.tnt.flashcards.database.NoteConfigurationRepository
import com.trippntechnology.tnt.flashcards.objects.note.Note
import com.trippntechnology.tnt.flashcards.objects.noteconfiguration.NoteConfiguration
import com.vikingsen.inject.viewmodel.ViewModelInject
import kotlinx.coroutines.launch
import timber.log.Timber

class NoteEditConfigViewModel @ViewModelInject constructor(private val noteConfigurationRepository: NoteConfigurationRepository) :
    BaseViewModel() {

    val noteConfig: LiveData<NoteConfiguration>

    private val noteConfigId = MutableLiveData<Long>()
    val viewModelEvent = SingleLiveEvent<Int>()


    init {
        noteConfig = AbsentLiveData.switchMap(noteConfigId) {
            Timber.d("Retrieving config ${noteConfigId.value} from database")
            noteConfigurationRepository.getNoteConfigByIdLiveData(it)
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


    fun deleteConfig(configId: Long): Boolean {
        if (configId != noteConfigId.value){
            return false
        }
        launch {
            noteConfigurationRepository.deleteNoteConfigById(configId)
            viewModelEvent.postValue(EVENT_FINISHED)
        }
        return true
    }

    companion object {
        const val EVENT_SAVE = 100
        const val EVENT_FINISHED = 101

    }
}