@file:Suppress("unused")

package com.trippntechnology.tnt.flashcards.injector

import com.vikingsen.inject.viewmodel.ViewModelModule
import dagger.Module

@ViewModelModule
@Module(includes = [ViewModelInject_AssistedModule::class])
abstract class AssistedModule
