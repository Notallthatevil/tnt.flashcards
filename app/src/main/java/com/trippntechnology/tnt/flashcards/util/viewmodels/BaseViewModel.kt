package com.trippntechnology.tnt.flashcards.util.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope by DefaultScope() {

    override fun onCleared() {
        cancel()
        super.onCleared()
    }
}