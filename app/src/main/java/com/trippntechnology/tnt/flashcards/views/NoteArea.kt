package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet

class NoteArea(
    context: Context,
    attrs: AttributeSet?
) : FlashCardArea(context, attrs) {




    fun draw(canvas: Canvas, noteCenterX: Float, noteCenterY: Float) {
        bounds.left = noteCenterX - NOTE_BASE_WIDTH / 2
        bounds.top = noteCenterY - NOTE_BASE_HEIGHT / 2
        bounds.right = noteCenterX + NOTE_BASE_WIDTH / 2
        bounds.bottom = noteCenterY + NOTE_BASE_HEIGHT / 2
        canvas.save()
        canvas.rotate(NOTE_BASE_ROTATION_ANGLE,noteCenterX,noteCenterY)
        draw(canvas)
        canvas.restore()
    }

    override fun draw(canvas: Canvas) {
        canvas.drawOval(bounds,blackPaint)
    }

}
