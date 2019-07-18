package com.trippntechnology.tnt.flashcards.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import com.trippntechnology.tnt.flashcards.objects.noteconfiguration.NoteConfiguration
import com.trippntechnology.tnt.flashcards.objects.enums.clefvalue.ClefValue
import com.trippntechnology.tnt.flashcards.objects.note.Note
import com.trippntechnology.tnt.flashcards.util.view.BaseView

class SelectionView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    BaseView(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private val selectableNotes = mutableListOf<SelectableNoteArea>()

    private val staffSpacing = LINE_SPACING * 5.5f

    init {
        Note.bassNotes.forEach {
            val selectableNote = SelectableNoteArea(context, STROKE_WIDTH, LINE_SPACING, it)
            selectableNotes.add(selectableNote)
        }
        Note.trebleNotes.forEach {
            val selectableNote = SelectableNoteArea(context, STROKE_WIDTH, LINE_SPACING, it)
            selectableNotes.add(selectableNote)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return
        val bottomStaffYCenter = height / 2f + staffSpacing / 2 + LINE_SPACING * 2
        val topStaffYCenter = height / 2f - staffSpacing / 2 - LINE_SPACING * 2

        //Draw staff
        staffArea.clef = ClefValue.TREBLE
        staffArea.setDrawSpace(
            0f + padding, topStaffYCenter - LINE_SPACING * 2, width.toFloat() - padding
        )
        staffArea.draw(canvas)

        staffArea.clef = ClefValue.BASS
        staffArea.setDrawSpace(
            0f + padding, bottomStaffYCenter - LINE_SPACING * 2, width.toFloat() - padding
        )
        staffArea.draw(canvas)

        //Draw notes
        val staffSpace =
            staffArea.bounds.right - (staffArea.bounds.left + staffArea.CLEF_WIDTH * 1.1f)
        val noteSpacing = staffSpace / 8
        val startXOffset = staffArea.bounds.left + staffArea.CLEF_WIDTH * 1.1f
        var xOffset = startXOffset
        for (i in 0 until selectableNotes.size) {
            if (i == 0 || i % 8 == 0) {
                xOffset = startXOffset
            } else {
                xOffset += noteSpacing
            }
            selectableNotes[i].setBounds(bottomStaffYCenter, topStaffYCenter, xOffset)
            selectableNotes[i].draw(canvas)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return true
        if (event.action == MotionEvent.ACTION_DOWN) {
            selectableNotes.forEach {
                if (it.bounds.contains(event.x, event.y)) {
                    it.select()
                    invalidate()
                }
            }
        }
        return true
    }

    fun loadConfig(noteConfiguration: NoteConfiguration?) {
        noteConfiguration ?: return
        val noteList = NoteConfiguration.stringToNotes(noteConfiguration.notes)
        if (noteList.isEmpty()) return
        selectableNotes.forEach {
            if (noteList.contains(it.note)) {
                it.select()
            }
        }
    }

    fun exportConfig(): List<Note> {
        val enabledNotes = mutableListOf<Note>()
        selectableNotes.forEach {
            if (it.isSelected()) {
                enabledNotes.add(it.note)
            }
        }
        return enabledNotes
    }
}