package com.trippntechnology.tnt.flashcards.ux.activities.main

import android.os.Bundle
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.objects.enums.ClefValue
import com.trippntechnology.tnt.flashcards.objects.enums.NoteValue
import com.trippntechnology.tnt.flashcards.util.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
