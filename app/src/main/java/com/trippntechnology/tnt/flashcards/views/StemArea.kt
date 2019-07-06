package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas

class StemArea(
    context: Context, strokeWidth: Float
) : FlashCardArea(context, strokeWidth) {

    override fun draw(canvas: Canvas) {
        canvas.drawRoundRect(bounds, dpToPx(12f), dpToPx(12f), basePaint)
    }
}