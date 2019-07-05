package com.trippntechnology.tnt.flashcards.ux.activities.main

import android.os.Bundle
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.objects.NoteValue
import com.trippntechnology.tnt.flashcards.util.activities.BaseActivity
import com.trippntechnology.tnt.flashcards.views.ClefArea
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        flashCard.setOnClickListener {
//            staffView.setNote(
//                ClefArea.Clef.values()[Random().nextInt(ClefArea.Clef.values().size)],
//                NoteValue.values()[Random().nextInt(NoteValue.values().size)]
//            )
//        }
    }
}
