package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.trippntechnology.tnt.flashcards.R
import kotlin.math.roundToInt

class ClefArea(
    context: Context,
    attrs: AttributeSet?
) : FlashCardArea(context, attrs) {


    //CLEFS
    private val TREBLE_HEIGHT = (LINE_SPACING * 6).roundToInt()
    private val BASS_HEIGHT = (LINE_SPACING*4).roundToInt()
    val CLEF_WIDTH = (LINE_SPACING * 3.5).roundToInt()

    private var clef = Clef.BASS
    private val bassClefDrawable = ContextCompat.getDrawable(context, R.drawable.bass)
    private val trebleClefDrawable = ContextCompat.getDrawable(context, R.drawable.treble)

    private val imageBounds = Rect()

    fun draw(canvas: Canvas, clef: Clef, centerYStaff: Float) {
        this.clef = clef
        imageBounds.left = (0-dpToPx(8f)).roundToInt()
        imageBounds.right = imageBounds.left + CLEF_WIDTH
        if (this.clef == Clef.BASS) {
            imageBounds.top = (centerYStaff - BASS_HEIGHT / 2-LINE_SPACING / 4).roundToInt()
            imageBounds.bottom = imageBounds.top + BASS_HEIGHT
        } else {
            imageBounds.top = (centerYStaff - TREBLE_HEIGHT / 2+STROKE_WIDTH).roundToInt()
            imageBounds.bottom = imageBounds.top + TREBLE_HEIGHT
        }
        draw(canvas)
    }

    override fun draw(canvas: Canvas) {
        if (clef == Clef.BASS) {
            bassClefDrawable?.bounds = imageBounds
            bassClefDrawable?.draw(canvas)
        } else {
            trebleClefDrawable?.bounds = imageBounds
            trebleClefDrawable?.draw(canvas)
        }
    }

    enum class Clef {
        BASS, TREBLE;
    }

}