package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import com.trippntechnology.tnt.flashcards.objects.ClefValue
import com.trippntechnology.tnt.flashcards.objects.NoteValue
import com.trippntechnology.tnt.flashcards.util.view.BaseView

class FlashCardView
    (context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    BaseView(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)


    private var clef = ClefValue.BASS

    /**
     * Just enough to give the note some space between the clef and the edge
     */
    private var noteXOffset = LINE_SPACING

    private var noteYOffset = CENTER_STAFF

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return
        val staffYCenter = height / 2f
        val staffXCenter = width / 2f
        val xOffset = staffXCenter + noteXOffset

        //Draw staff
        staffArea.clef = clef
        staffArea.bounds.top = staffYCenter - LINE_SPACING * 2
        staffArea.bounds.bottom = staffArea.bounds.top
        staffArea.bounds.left = 0f + padding
        staffArea.bounds.right = width.toFloat() - padding
        staffArea.draw(canvas)

        //Draw note
        noteArea.bounds.left = xOffset - NOTE_BASE_WIDTH / 2
        noteArea.bounds.top = staffYCenter + noteYOffset - NOTE_BASE_HEIGHT / 2
        noteArea.bounds.right = xOffset + NOTE_BASE_WIDTH / 2
        noteArea.bounds.bottom = staffYCenter + noteYOffset + NOTE_BASE_HEIGHT / 2
        noteArea.staffYCenter = staffYCenter
        noteArea.draw(canvas)
    }

    fun setNote(clef: ClefValue, note: NoteValue) {
        when (clef) {
            ClefValue.TREBLE -> when (note) {
                NoteValue.X_LOW_C -> noteYOffset = FULL_STEP_DOWN * 9 + HALF_STEP_DOWN
                NoteValue.X_LOW_D -> noteYOffset = FULL_STEP_DOWN * 9
                NoteValue.X_LOW_E -> noteYOffset = FULL_STEP_DOWN * 8 + HALF_STEP_DOWN
                NoteValue.X_LOW_F -> noteYOffset = FULL_STEP_DOWN * 8
                NoteValue.X_LOW_G -> noteYOffset = FULL_STEP_DOWN * 7 + HALF_STEP_DOWN
                NoteValue.LOW_A -> noteYOffset = FULL_STEP_DOWN * 7
                NoteValue.LOW_B -> noteYOffset = FULL_STEP_DOWN * 6 + HALF_STEP_DOWN
                NoteValue.LOW_C -> noteYOffset = FULL_STEP_DOWN * 6
                NoteValue.LOW_D -> noteYOffset = FULL_STEP_DOWN * 5 + HALF_STEP_DOWN
                NoteValue.LOW_E -> noteYOffset = FULL_STEP_DOWN * 5
                NoteValue.LOW_F -> noteYOffset = FULL_STEP_DOWN * 4 + HALF_STEP_DOWN
                NoteValue.MIDDLE_A -> noteYOffset = FULL_STEP_DOWN * 4
                NoteValue.MIDDLE_B -> noteYOffset = FULL_STEP_DOWN * 3 + HALF_STEP_DOWN
                NoteValue.MIDDLE_C -> noteYOffset = FULL_STEP_DOWN * 3
                NoteValue.MIDDLE_D -> noteYOffset = FULL_STEP_DOWN * 2 + HALF_STEP_DOWN
                NoteValue.MIDDLE_E -> noteYOffset = FULL_STEP_DOWN * 2
                NoteValue.MIDDLE_F -> noteYOffset = FULL_STEP_DOWN + HALF_STEP_DOWN
                NoteValue.MIDDLE_G -> noteYOffset = FULL_STEP_DOWN
                NoteValue.HIGH_A -> noteYOffset = HALF_STEP_DOWN
                NoteValue.HIGH_B -> noteYOffset = CENTER_STAFF
                NoteValue.HIGH_C -> noteYOffset = HALF_STEP_UP
                NoteValue.HIGH_D -> noteYOffset = FULL_STEP_UP
                NoteValue.HIGH_E -> noteYOffset = FULL_STEP_UP + HALF_STEP_UP
                NoteValue.HIGH_F -> noteYOffset = FULL_STEP_UP * 2
                NoteValue.HIGH_G -> noteYOffset = FULL_STEP_UP * 2 + HALF_STEP_UP
                NoteValue.X_HIGH_A -> noteYOffset = FULL_STEP_UP * 3
                NoteValue.X_HIGH_B -> noteYOffset = FULL_STEP_UP * 3 + HALF_STEP_UP
                NoteValue.X_HIGH_C -> noteYOffset = FULL_STEP_UP * 4
            }
            ClefValue.BASS -> when (note) {
                NoteValue.X_LOW_C -> noteYOffset = FULL_STEP_DOWN * 4
                NoteValue.X_LOW_D -> noteYOffset = FULL_STEP_DOWN * 3 + HALF_STEP_DOWN
                NoteValue.X_LOW_E -> noteYOffset = FULL_STEP_DOWN * 3
                NoteValue.X_LOW_F -> noteYOffset = FULL_STEP_DOWN * 2 + HALF_STEP_DOWN
                NoteValue.X_LOW_G -> noteYOffset = FULL_STEP_DOWN * 2
                NoteValue.LOW_A -> noteYOffset = FULL_STEP_DOWN + HALF_STEP_DOWN
                NoteValue.LOW_B -> noteYOffset = FULL_STEP_DOWN
                NoteValue.LOW_C -> noteYOffset = HALF_STEP_DOWN
                NoteValue.LOW_D -> noteYOffset = CENTER_STAFF
                NoteValue.LOW_E -> noteYOffset = HALF_STEP_UP
                NoteValue.LOW_F -> noteYOffset = FULL_STEP_UP
                NoteValue.MIDDLE_A -> noteYOffset = FULL_STEP_UP + HALF_STEP_UP
                NoteValue.MIDDLE_B -> noteYOffset = FULL_STEP_UP * 2
                NoteValue.MIDDLE_C -> noteYOffset = FULL_STEP_UP * 2 + HALF_STEP_UP
                NoteValue.MIDDLE_D -> noteYOffset = FULL_STEP_UP * 3
                NoteValue.MIDDLE_E -> noteYOffset = FULL_STEP_UP * 3 + HALF_STEP_UP
                NoteValue.MIDDLE_F -> noteYOffset = FULL_STEP_UP * 4
                NoteValue.MIDDLE_G -> noteYOffset = FULL_STEP_UP * 4 + HALF_STEP_UP
                NoteValue.HIGH_A -> noteYOffset = FULL_STEP_UP * 5
                NoteValue.HIGH_B -> noteYOffset = FULL_STEP_UP * 5 + HALF_STEP_UP
                NoteValue.HIGH_C -> noteYOffset = FULL_STEP_UP * 6
                NoteValue.HIGH_D -> noteYOffset = FULL_STEP_UP * 6 + HALF_STEP_UP
                NoteValue.HIGH_E -> noteYOffset = FULL_STEP_UP * 7
                NoteValue.HIGH_F -> noteYOffset = FULL_STEP_UP * 7 + HALF_STEP_UP
                NoteValue.HIGH_G -> noteYOffset = FULL_STEP_UP * 8
                NoteValue.X_HIGH_A -> noteYOffset = FULL_STEP_UP * 8 + HALF_STEP_UP
                NoteValue.X_HIGH_B -> noteYOffset = FULL_STEP_UP * 9
                NoteValue.X_HIGH_C -> noteYOffset = FULL_STEP_UP * 9 + HALF_STEP_UP
            }
        }
        this.clef = clef
        invalidate()
    }
}