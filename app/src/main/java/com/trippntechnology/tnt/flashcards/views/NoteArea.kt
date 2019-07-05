package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas

class NoteArea(
    context: Context,strokeWidth:Float
) : FlashCardArea(context,strokeWidth) {

    override fun draw(canvas: Canvas) {
        canvas.drawOval(bounds,blackPaint)
    }

}
