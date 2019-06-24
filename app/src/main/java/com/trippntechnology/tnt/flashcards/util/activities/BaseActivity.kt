package com.trippntechnology.tnt.flashcards.util.activities

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


abstract class BaseActivity : LiveDataObserverActivity(), CoroutineScope by MainScope() {
    override fun onDestroy() {
        super.onDestroy()
        cancel() // CoroutineScope.cancel
    }
}