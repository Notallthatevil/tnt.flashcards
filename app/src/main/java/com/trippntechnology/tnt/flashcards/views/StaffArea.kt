package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import com.trippntechnology.tnt.flashcards.objects.ClefValue

class StaffArea(
    context: Context, strokeWidth: Float, private val lineSpacing: Float
) : FlashCardArea(context, strokeWidth) {


    var clef = ClefValue.BASS

    private val clefArea = ClefArea(context,strokeWidth)
    private val points = FloatArray(20)

    private val TREBLE_HEIGHT = (lineSpacing * 6)
    private val BASS_HEIGHT = (lineSpacing * 4)
    private val CLEF_WIDTH = (lineSpacing * 3.5f)

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

        clefArea.clef = clef
        clefArea.bounds.left = 0f
        clefArea.bounds.right = clefArea.bounds.left + CLEF_WIDTH
        if (clef == ClefValue.BASS) {
            clefArea.bounds.top = (points[9] - BASS_HEIGHT / 2 - lineSpacing / 4)
            clefArea.bounds.bottom = clefArea.bounds.top + BASS_HEIGHT
        } else {
            clefArea.bounds.top = (points[9] - TREBLE_HEIGHT / 2 + strokeWidth)
            clefArea.bounds.bottom = clefArea.bounds.top + TREBLE_HEIGHT
        }
        clefArea.draw(canvas)
    }
}