package com.trippntechnology.tnt.flashcards.ux.activities.main

import android.os.Bundle
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.objects.ClefValue
import com.trippntechnology.tnt.flashcards.objects.NoteValue
import com.trippntechnology.tnt.flashcards.util.activities.BaseActivity
import com.trippntechnology.tnt.flashcards.views.ClefArea
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        flashCardCardView.setOnClickListener {
            flashCard.setNote(
                ClefValue.values()[Random().nextInt(ClefValue.values().size)],
                NoteValue.values()[Random().nextInt(NoteValue.values().size)]
            )
        }
    }
}
