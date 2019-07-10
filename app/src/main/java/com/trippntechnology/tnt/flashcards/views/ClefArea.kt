package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.objects.enums.clefvalue.ClefValue

class ClefArea(
    context: Context,strokeWidth:Float
) : FlashCardArea(context, strokeWidth) {

    var clef = ClefValue.BASS

    private val bassClefDrawable =  ContextCompat.getDrawable(context,R.drawable.bass)
    private val trebleClefDrawable = ContextCompat.getDrawable(context, R.drawable.treble)


    override fun draw(canvas: Canvas) {
        if (clef == ClefValue.BASS) {
            bassClefDrawable ?: return
            bounds.round(bassClefDrawable.bounds)
            bassClefDrawable.draw(canvas)
        } else {
            trebleClefDrawable?:return
            bounds.round(trebleClefDrawable.bounds)
            trebleClefDrawable.draw(canvas)
        }
    }
}