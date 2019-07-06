package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.objects.ClefValue
import com.trippntechnology.tnt.flashcards.objects.Note

class SelectableNoteArea(
    context: Context,
    strokeWidth: Float,
    private val lineSpace: Float,
    val note: Note
) :
    NoteArea(context, strokeWidth, lineSpace) {

    private var selected = false
    private var yBoundsSet = false

    init {
        drawStem = false
        posSet = true
    }

    fun setBounds(bottomStaffYCenter: Float, topStaffYCenter: Float, noteCenterXPos: Float) {
        yBoundsSet = true
        staffYCenter = if (note.clef == ClefValue.BASS) {
            bottomStaffYCenter
        } else {
            topStaffYCenter
        }
        bounds.top = staffYCenter +Note.getNoteTranslation(note, lineSpace) - NOTE_BASE_HEIGHT / 2
        bounds.left = noteCenterXPos - NOTE_BASE_WIDTH / 2
        bounds.bottom = bounds.top + NOTE_BASE_HEIGHT
        bounds.right = bounds.left + NOTE_BASE_WIDTH
    }

    override fun draw(canvas: Canvas) {
        if (!yBoundsSet) {
            throw IllegalArgumentException("You must call setBounds before you can draw")
        }
        super.draw(canvas)
    }

    fun select() {
        selected = !selected
        if (selected) {
            basePaint.color = ContextCompat.getColor(context, R.color.selectedStroke)
            basePaint.setShadowLayer(
                dpToPx(8f),
                0f,
                dpToPx(4f),
                ContextCompat.getColor(context, R.color.selectedStrokeShadow)
            )
        } else {
            basePaint.color = ContextCompat.getColor(context, R.color.darkStroke)
            basePaint.setShadowLayer(
                dpToPx(8f),
                0f,
                dpToPx(4f),
                ContextCompat.getColor(context, R.color.darkStrokeShadow)
            )
        }
    }
}