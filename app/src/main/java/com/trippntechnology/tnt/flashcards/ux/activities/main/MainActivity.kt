package com.trippntechnology.tnt.flashcards.ux.activities.main

import android.os.Bundle
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.util.activities.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        flashCard.setOnClickListener {
//            staffView.setNote(
//                NoteView.TREBLE_CLEF,
//                NoteValue.values()[Random().nextInt(NoteValue.values().size)]
//            )
//        }
    }
}
