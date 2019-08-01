package com.trippntechnology.tnt.flashcards.ux.activities.main

import android.os.Bundle
import androidx.navigation.findNavController
import com.trippntechnology.tnt.androidbaseutils.ux.activities.BaseActivity
import com.trippntechnology.tnt.flashcards.R

class MainActivity : BaseActivity() {

    private val navController by lazy { findNavController(R.id.mainNavHostFragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

}
