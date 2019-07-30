package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import kotlin.math.roundToInt

open class NoteArea(
    context: Context, strokeWidth: Float, private val lineSpace: Float
) : FlashCardArea(context, strokeWidth) {

    //Note size
    val NOTE_BASE_HEIGHT = lineSpace - strokeWidth
    val NOTE_BASE_WIDTH = lineSpace * 1.5f - strokeWidth

    var staffYCenter = -1f
    protected var posSet = false
    protected var drawStem = true


    val stemArea = StemArea(context, strokeWidth)
    val ledgerLineArea = LedgerLineArea(context, strokeWidth)


    override fun draw(canvas: Canvas) {
//        if (staffYCenter < 0f) {
//            throw IllegalArgumentException("Need to set staffYCenter before calling draw")
//        }
        if (!posSet) {
            throw IllegalArgumentException("You must call setDrawSpace before you can draw")
        }

        ledgerLineArea.bounds.left = bounds.left
        ledgerLineArea.bounds.right = bounds.right
        if (bounds.bottom < staffYCenter) {
            //Stem down
            stemArea.bounds.left = bounds.left + strokeWidth * .35f
            stemArea.bounds.top = bounds.centerY() + strokeWidth / 2
            stemArea.bounds.right = stemArea.bounds.left + strokeWidth
            stemArea.bounds.bottom = stemArea.bounds.top + lineSpace * 4


            val numberOfLines =
                (((staffYCenter - lineSpace * 3) - bounds.top + lineSpace / 2) / lineSpace).roundToInt()

            for (i in 0 until numberOfLines) {
                ledgerLineArea.bounds.bottom = (staffYCenter - lineSpace * 3) - lineSpace * i
                ledgerLineArea.bounds.top = ledgerLineArea.bounds.bottom
                ledgerLineArea.draw(canvas)
            }
        } else {
            //Stem up
            stemArea.bounds.right = bounds.right - strokeWidth * .35f
            stemArea.bounds.left = stemArea.bounds.right - strokeWidth
            stemArea.bounds.bottom = bounds.centerY() - strokeWidth / 2
            stemArea.bounds.top = stemArea.bounds.bottom - lineSpace * 4

            val numberOfLines =
                ((bounds.bottom - (staffYCenter + lineSpace * 3) + lineSpace / 2) / lineSpace).roundToInt()

            for (i in 0 until numberOfLines) {
                ledgerLineArea.bounds.bottom = (staffYCenter + lineSpace * 3) + lineSpace * i
                ledgerLineArea.bounds.top = ledgerLineArea.bounds.bottom
                ledgerLineArea.draw(canvas)
            }
        }
        if (drawStem) {
            stemArea.draw(canvas)
        }
        canvas.save()
        canvas.rotate(
            NOTE_BASE_ROTATION_ANGLE,
            bounds.centerX(),
            bounds.centerY()
        )
        canvas.drawOval(bounds, basePaint)
        canvas.restore()
    }

    fun setDrawSpace(noteCenterX:Float,noteCenterY:Float){
        posSet = true
        bounds.left = noteCenterX - NOTE_BASE_WIDTH / 2
        bounds.right = bounds.left + NOTE_BASE_WIDTH
        bounds.top = noteCenterY - NOTE_BASE_HEIGHT / 2
        bounds.bottom = bounds.top + NOTE_BASE_HEIGHT
    }

    companion object {
        private const val NOTE_BASE_ROTATION_ANGLE = 340f
    }

}
