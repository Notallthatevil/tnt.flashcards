package com.trippntechnology.tnt.flashcards.injector

import android.app.Application
import com.trippntechnology.tnt.flashcards.util.CoroutineContextProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AssistedModule::class])
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    fun provideCoroutineContextProvider(): CoroutineContextProvider {
        return CoroutineContextProvider.MainCoroutineContextProvider
    }

}
