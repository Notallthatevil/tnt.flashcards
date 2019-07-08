package com.trippntechnology.tnt.flashcards.injector


import android.app.Application
import com.trippntechnology.tnt.flashcards.ux.activities.main.MainActivity
import com.trippntechnology.tnt.flashcards.ux.fragments.main.FlashCardFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(application: App)

    // UI
    fun inject(target: MainActivity)

    fun inject(fragment: FlashCardFragment)


    // Exported for child-components.
    fun application(): Application
}
