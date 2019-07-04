package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet

class StaffArea(
    context: Context,
    attrs: AttributeSet?
) : FlashCardArea(context, attrs) {

    private val points = FloatArray(20)

    //NOTE POSITION
    val HALF_STEP_UP = -LINE_SPACING / 2
    val HALF_STEP_DOWN = LINE_SPACING / 2
    val FULL_STEP_UP = -LINE_SPACING
    val FULL_STEP_DOWN = LINE_SPACING
    val CENTER_STAFF = 0f

    fun draw(canvas: Canvas, midYCanvas: Float, canvasWidth: Float) {
        bounds.top = midYCanvas - LINE_SPACING * 2
        bounds.bottom = bounds.top
        bounds.left = 0f + STAFF_PADDING
        bounds.right = canvasWidth - STAFF_PADDING
        canvas.drawRect(bounds, blackPaint)
        draw(canvas)
    }

    override fun draw(canvas: Canvas) {
        var yPos = bounds.top
        var pass = 0
        for (i in 0 until points.size) {
            if (i % 4 == 0 || i == 0) {
                points[i] = bounds.left
            } else if (i % 2 == 0) {
                points[i] = bounds.right
            } else if (i % 2 != 0) {
                points[i] = yPos
                pass++
                if (pass == 2) {
                    pass = 0
                    yPos += LINE_SPACING
                }
            }
        }
        canvas.drawLines(points, blackPaint)
    }
}