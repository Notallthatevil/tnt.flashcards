package com.trippntechnology.tnt.flashcards.util.fragments

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment : LiveDataObserverFragment(), CoroutineScope by MainScope() {
    override fun onDestroy() {
        super.onDestroy()
        cancel() // CoroutineScope.cancel
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpObservers()
    }

    protected abstract fun setUpObservers()
}