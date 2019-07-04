package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet

class StemArea(
    context: Context,
    attrs: AttributeSet?
) : FlashCardArea(context, attrs) {

    private val NOTE_STEM_HEIGHT = LINE_SPACING * 4
    private val NOTE_STEM_WIDTH = STROKE_WIDTH
    private val NOTE_STEM_CORNER_RADIUS = STROKE_WIDTH


    fun draw(canvas: Canvas, noteCenterX: Float, noteCenterY: Float, staffYCenter: Float) {
        if (noteCenterY < staffYCenter) {
            bounds.left = noteCenterX - NOTE_BASE_WIDTH / 2+STROKE_WIDTH/4
            bounds.top = noteCenterY+STROKE_WIDTH
            bounds.right = bounds.left + NOTE_STEM_WIDTH
            bounds.bottom = bounds.top + NOTE_STEM_HEIGHT
        } else {
            bounds.left = noteCenterX + NOTE_BASE_WIDTH / 2-STROKE_WIDTH*1.25f
            bounds.bottom = noteCenterY-STROKE_WIDTH
            bounds.right = bounds.left + NOTE_STEM_WIDTH
            bounds.top = bounds.bottom-NOTE_STEM_HEIGHT
        }
        draw(canvas)
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRoundRect(bounds, NOTE_STEM_CORNER_RADIUS, NOTE_STEM_CORNER_RADIUS, blackPaint)
    }
}