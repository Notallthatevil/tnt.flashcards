package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.trippntechnology.tnt.flashcards.R

class ClefArea(
    context: Context,strokeWidth:Float
) : FlashCardArea(context, strokeWidth) {

    var clef = Clef.BASS

    private val bassClefDrawable = ContextCompat.getDrawable(context, R.drawable.bass)
    private val trebleClefDrawable = ContextCompat.getDrawable(context, R.drawable.treble)


    override fun draw(canvas: Canvas) {
        if (clef == Clef.BASS) {
            bassClefDrawable ?: return
            bounds.round(bassClefDrawable.bounds)
            bassClefDrawable.draw(canvas)
        } else {
            trebleClefDrawable?:return
            bounds.round(trebleClefDrawable.bounds)
            trebleClefDrawable.draw(canvas)
        }
    }

    enum class Clef {
        BASS, TREBLE;
    }
}