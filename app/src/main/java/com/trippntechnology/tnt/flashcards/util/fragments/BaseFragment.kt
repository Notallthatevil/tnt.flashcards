package com.trippntechnology.tnt.flashcards.util.fragments

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment : LiveDataObserverFragment(), CoroutineScope by MainScope() {
    override fun onDestroy() {
        super.onDestroy()
        cancel() // CoroutineScope.cancel
    }
}