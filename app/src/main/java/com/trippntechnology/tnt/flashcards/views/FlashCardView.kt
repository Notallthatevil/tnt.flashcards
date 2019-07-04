package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.util.view.BaseView


class FlashCardView
    (context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    BaseView(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)


    private var clef: ClefArea.Clef = ClefArea.Clef.BASS

    private val lineSpacing = staffArea.LINE_SPACING

    /**
     * Just enough to give the note some space between the clef and the edge
     */
    private var noteXOffset = lineSpacing
    private var noteYOffset = staffArea.CENTER_STAFF

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return
        val staffYCenter = height / 2f
        val widthCenter = width / 2f
        val xOffset = widthCenter + noteXOffset
        staffArea.draw(canvas, staffYCenter, width.toFloat())
        clefArea.draw(canvas, clef, staffYCenter)
        noteArea.draw(canvas, xOffset, staffYCenter + noteYOffset)
        stemArea.draw(canvas, xOffset, staffYCenter + noteYOffset, staffYCenter)
        ledgerLineArea.draw(canvas, xOffset, staffYCenter + noteYOffset, staffYCenter)
    }


//    fun setNote(clef: Int, note: NoteValue) {
//        when (clef) {
//            NoteView.TREBLE_CLEF -> when (note) {
//                NoteValue.X_LOW_C -> noteTranslation = FULL_STEP_DOWN * 9 + HALF_STEP_DOWN
//                NoteValue.X_LOW_D -> noteTranslation = FULL_STEP_DOWN * 9
//                NoteValue.X_LOW_E -> noteTranslation = FULL_STEP_DOWN * 8 + HALF_STEP_DOWN
//                NoteValue.X_LOW_F -> noteTranslation = FULL_STEP_DOWN * 8
//                NoteValue.X_LOW_G -> noteTranslation = FULL_STEP_DOWN * 7 + HALF_STEP_DOWN
//                NoteValue.LOW_A -> noteTranslation = FULL_STEP_DOWN * 7
//                NoteValue.LOW_B -> noteTranslation = FULL_STEP_DOWN * 6 + HALF_STEP_DOWN
//                NoteValue.LOW_C -> noteTranslation = FULL_STEP_DOWN * 6
//                NoteValue.LOW_D -> noteTranslation = FULL_STEP_DOWN * 5 + HALF_STEP_DOWN
//                NoteValue.LOW_E -> noteTranslation = FULL_STEP_DOWN * 5
//                NoteValue.LOW_F -> noteTranslation = FULL_STEP_DOWN * 4 + HALF_STEP_DOWN
//                NoteValue.MIDDLE_A -> noteTranslation = FULL_STEP_DOWN * 4
//                NoteValue.MIDDLE_B -> noteTranslation = FULL_STEP_DOWN * 3 + HALF_STEP_DOWN
//                NoteValue.MIDDLE_C -> noteTranslation = FULL_STEP_DOWN * 3
//                NoteValue.MIDDLE_D -> noteTranslation = FULL_STEP_DOWN * 2 + HALF_STEP_DOWN
//                NoteValue.MIDDLE_E -> noteTranslation = FULL_STEP_DOWN * 2
//                NoteValue.MIDDLE_F -> noteTranslation = FULL_STEP_DOWN + HALF_STEP_DOWN
//                NoteValue.MIDDLE_G -> noteTranslation = FULL_STEP_DOWN
//                NoteValue.HIGH_A -> noteTranslation = HALF_STEP_DOWN
//                NoteValue.HIGH_B -> noteTranslation = CENTER_STAFF
//                NoteValue.HIGH_C -> noteTranslation = HALF_STEP_UP
//                NoteValue.HIGH_D -> noteTranslation = FULL_STEP_UP
//                NoteValue.HIGH_E -> noteTranslation = FULL_STEP_UP + HALF_STEP_UP
//                NoteValue.HIGH_F -> noteTranslation = FULL_STEP_UP * 2
//                NoteValue.HIGH_G -> noteTranslation = FULL_STEP_UP * 2 + HALF_STEP_UP
//                NoteValue.X_HIGH_A -> noteTranslation = FULL_STEP_UP * 3
//                NoteValue.X_HIGH_B -> noteTranslation = FULL_STEP_UP * 3 + HALF_STEP_UP
//                NoteValue.X_HIGH_C -> noteTranslation = FULL_STEP_UP * 4
//            }
//            NoteView.BASS_CLEF -> when (note) {
//                NoteValue.X_LOW_C -> noteTranslation = FULL_STEP_DOWN * 4
//                NoteValue.X_LOW_D -> noteTranslation = FULL_STEP_DOWN * 3 + HALF_STEP_DOWN
//                NoteValue.X_LOW_E -> noteTranslation = FULL_STEP_DOWN * 3
//                NoteValue.X_LOW_F -> noteTranslation = FULL_STEP_DOWN * 2 + HALF_STEP_DOWN
//                NoteValue.X_LOW_G -> noteTranslation = FULL_STEP_DOWN * 2
//                NoteValue.LOW_A -> noteTranslation = FULL_STEP_DOWN + HALF_STEP_DOWN
//                NoteValue.LOW_B -> noteTranslation = FULL_STEP_DOWN
//                NoteValue.LOW_C -> noteTranslation = HALF_STEP_DOWN
//                NoteValue.LOW_D -> noteTranslation = CENTER_STAFF
//                NoteValue.LOW_E -> noteTranslation = HALF_STEP_UP
//                NoteValue.LOW_F -> noteTranslation = FULL_STEP_UP
//                NoteValue.MIDDLE_A -> noteTranslation = FULL_STEP_UP + HALF_STEP_UP
//                NoteValue.MIDDLE_B -> noteTranslation = FULL_STEP_UP * 2
//                NoteValue.MIDDLE_C -> noteTranslation = FULL_STEP_UP * 2 + HALF_STEP_UP
//                NoteValue.MIDDLE_D -> noteTranslation = FULL_STEP_UP * 3
//                NoteValue.MIDDLE_E -> noteTranslation = FULL_STEP_UP * 3 + HALF_STEP_UP
//                NoteValue.MIDDLE_F -> noteTranslation = FULL_STEP_UP * 4
//                NoteValue.MIDDLE_G -> noteTranslation = FULL_STEP_UP * 4 + HALF_STEP_UP
//                NoteValue.HIGH_A -> noteTranslation = FULL_STEP_UP * 5
//                NoteValue.HIGH_B -> noteTranslation = FULL_STEP_UP * 5 + HALF_STEP_UP
//                NoteValue.HIGH_C -> noteTranslation = FULL_STEP_UP * 6
//                NoteValue.HIGH_D -> noteTranslation = FULL_STEP_UP * 6 + HALF_STEP_UP
//                NoteValue.HIGH_E -> noteTranslation = FULL_STEP_UP * 7
//                NoteValue.HIGH_F -> noteTranslation = FULL_STEP_UP * 7 + HALF_STEP_UP
//                NoteValue.HIGH_G -> noteTranslation = FULL_STEP_UP * 8
//                NoteValue.X_HIGH_A -> noteTranslation = FULL_STEP_UP * 8 + HALF_STEP_UP
//                NoteValue.X_HIGH_B -> noteTranslation = FULL_STEP_UP * 9
//                NoteValue.X_HIGH_C -> noteTranslation = FULL_STEP_UP * 9 + HALF_STEP_UP
//
//            }
//            else -> throw IllegalArgumentException("clef must equal TREBLE_CLEF or BASS_CLEF")
//        }
//        this.clef = clef
//        invalidate()
//    }
}