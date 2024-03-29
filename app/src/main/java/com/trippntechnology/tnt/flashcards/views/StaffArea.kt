package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import com.trippntechnology.tnt.flashcards.objects.enums.clefvalue.ClefValue

class StaffArea(
    context: Context, strokeWidth: Float, private val lineSpacing: Float
) : FlashCardArea(context, strokeWidth) {


    var clef = ClefValue.BASS

    private val clefArea = ClefArea(context,strokeWidth)
    private val points = FloatArray(20)

    private val TREBLE_HEIGHT = (lineSpacing * 6)
    private val BASS_HEIGHT = (lineSpacing * 4)
    val CLEF_WIDTH = (lineSpacing * 3.5f)

    private var posSet = false

    override fun draw(canvas: Canvas) {
        if (!posSet) {
            throw IllegalArgumentException("You must call setDrawSpace before you can draw")
        }

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
        canvas.drawLines(points, basePaint)

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

    fun setDrawSpace(left:Float,topLineYPos:Float,right:Float){
        posSet = true
        bounds.left = left
        bounds.right = right
        bounds.top = topLineYPos
        bounds.bottom = bounds.top
    }
}