package com.trippntechnology.tnt.flashcards.util.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.vikingsen.inject.viewmodel.ViewModelInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

abstract class BaseAndroidViewModel @ViewModelInject constructor(application: Application) : AndroidViewModel(application),
    CoroutineScope by DefaultScope() {

    override fun onCleared() {
        cancel()
        super.onCleared()
    }
}
