package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas

class LedgerLineArea(
    context: Context,strokeWidth:Float
) : FlashCardArea(context,strokeWidth) {

    override fun draw(canvas: Canvas) {
        canvas.drawLine(bounds.left, bounds.top, bounds.right, bounds.bottom, basePaint)
    }
}