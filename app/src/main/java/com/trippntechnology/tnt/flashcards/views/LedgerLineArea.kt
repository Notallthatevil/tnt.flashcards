package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import kotlin.math.floor

class LedgerLineArea(
    context: Context,
    attrs: AttributeSet?
) : FlashCardArea(context, attrs) {


    fun draw(canvas: Canvas, currentXOffset: Float, currentYOffset: Float, centerYStaff: Float) {
        bounds.left = currentXOffset - NOTE_BASE_WIDTH / 2
        bounds.right = currentXOffset + NOTE_BASE_WIDTH / 2
        var ledgerLine = 0
        val offsetBelow = centerYStaff + LINE_SPACING * 3
        var noteOffset = currentYOffset
        while (floor(noteOffset) >= floor(offsetBelow)) {
            bounds.top = offsetBelow + LINE_SPACING * ledgerLine
            bounds.bottom = bounds.top
            noteOffset -= LINE_SPACING
            ledgerLine++
            draw(canvas)
        }
        val offsetAbove = centerYStaff - LINE_SPACING * 3
        while (floor(noteOffset) <= floor(offsetAbove)) {
            bounds.top = offsetAbove - LINE_SPACING * ledgerLine
            bounds.bottom = bounds.top
            noteOffset += LINE_SPACING
            ledgerLine++
            draw(canvas)
        }
    }

    override fun draw(canvas: Canvas) {
        canvas.drawLine(bounds.left, bounds.top, bounds.right, bounds.bottom, blackPaint)
    }
}