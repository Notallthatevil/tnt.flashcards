package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import kotlin.math.floor

class LedgerLineArea(
    context: Context,strokeWidth:Float
) : FlashCardArea(context,strokeWidth) {

    override fun draw(canvas: Canvas) {
        canvas.drawLine(bounds.left, bounds.top, bounds.right, bounds.bottom, blackPaint)
    }
}