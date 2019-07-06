package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import com.trippntechnology.tnt.flashcards.objects.ClefValue
import com.trippntechnology.tnt.flashcards.objects.Note
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
        noteYOffset = Note.getNoteTranslation(Note(clef,note),LINE_SPACING)
        this.clef = clef
        invalidate()
    }
}