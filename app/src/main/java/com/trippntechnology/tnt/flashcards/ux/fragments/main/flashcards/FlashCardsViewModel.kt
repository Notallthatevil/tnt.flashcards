package com.trippntechnology.tnt.flashcards.ux.fragments.main.flashcards

import android.view.View
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import com.trippntechnology.tnt.androidbaseutils.ux.livedata.SingleLiveEvent
import com.trippntechnology.tnt.androidbaseutils.ux.viewmodels.BaseViewModel
import com.trippntechnology.tnt.flashcards.database.NoteConfigurationRepository
import com.trippntechnology.tnt.flashcards.objects.note.Note
import com.trippntechnology.tnt.flashcards.objects.noteconfiguration.NoteConfiguration
import com.vikingsen.inject.viewmodel.ViewModelInject
import kotlinx.coroutines.launch

class FlashCardsViewModel @ViewModelInject constructor(private val noteConfigurationRepository: NoteConfigurationRepository) :
    BaseViewModel() {

    val cardSelected = MutableLiveData<Note>()
    val submitAnswer = MutableLiveData<String>()
    val loadedConfig = SingleLiveEvent<NoteConfiguration>()

    fun loadPacket(configId:Long){
        launch {
             loadedConfig.postValue(noteConfigurationRepository.getNoteConfigById(configId))
        }
    }

    fun submitAnswer(v:View){
        val b = v as Button
        submitAnswer.value = b.text.toString()
    }
}