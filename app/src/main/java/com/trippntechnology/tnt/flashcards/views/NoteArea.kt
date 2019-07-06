package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import kotlin.math.floor
import kotlin.math.roundToInt

class NoteArea(
    context: Context, strokeWidth: Float, private val lineSpace: Float
) : FlashCardArea(context, strokeWidth) {

    var staffYCenter = -1f

    val stemArea = StemArea(context, strokeWidth)
    val ledgerLineArea = LedgerLineArea(context, strokeWidth)


    override fun draw(canvas: Canvas) {
        if (staffYCenter < 0f) {
            throw IllegalArgumentException("Need to set staffYCenter before calling draw")
        }

        ledgerLineArea.bounds.left = bounds.left
        ledgerLineArea.bounds.right = bounds.right
        if (bounds.bottom < staffYCenter) {
            //Stem down
            stemArea.bounds.left = bounds.left + strokeWidth * .35f
            stemArea.bounds.top = bounds.centerY() + strokeWidth / 2
            stemArea.bounds.right = stemArea.bounds.left + strokeWidth
            stemArea.bounds.bottom = stemArea.bounds.top + lineSpace * 4


            val numberOfLines = (((staffYCenter-lineSpace*3)-bounds.top+lineSpace/2)/lineSpace).roundToInt()

            for (i in  0 until numberOfLines){
                ledgerLineArea.bounds.bottom = (staffYCenter-lineSpace*3)-lineSpace*i
                ledgerLineArea.bounds.top = ledgerLineArea.bounds.bottom
                ledgerLineArea.draw(canvas)
            }
        } else {
            //Stem up
            stemArea.bounds.right = bounds.right - strokeWidth * .35f
            stemArea.bounds.left = stemArea.bounds.right - strokeWidth
            stemArea.bounds.bottom = bounds.centerY() - strokeWidth / 2
            stemArea.bounds.top = stemArea.bounds.bottom - lineSpace * 4

            val numberOfLines = ((bounds.bottom-(staffYCenter+lineSpace*3)+lineSpace/2)/lineSpace).roundToInt()

            for (i in  0 until numberOfLines){
                ledgerLineArea.bounds.bottom = (staffYCenter+lineSpace*3)+lineSpace*i
                ledgerLineArea.bounds.top = ledgerLineArea.bounds.bottom
                ledgerLineArea.draw(canvas)
            }
        }
        stemArea.draw(canvas)
        canvas.save()
        canvas.rotate(
            NOTE_BASE_ROTATION_ANGLE,
            bounds.centerX(),
            bounds.centerY()
        )
        canvas.drawOval(bounds, blackPaint)
        canvas.restore()
    }

    companion object {
        private const val NOTE_BASE_ROTATION_ANGLE = 340f
    }

}
