package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas

class StaffArea(
    context: Context,strokeWidth:Float, private val lineSpacing: Float
) : FlashCardArea(context,strokeWidth) {

    private val points = FloatArray(20)

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
                    yPos += lineSpacing
                }
            }
        }
        canvas.drawLines(points, blackPaint)
    }
}