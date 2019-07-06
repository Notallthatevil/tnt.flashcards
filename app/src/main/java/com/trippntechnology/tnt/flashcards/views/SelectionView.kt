package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import com.trippntechnology.tnt.flashcards.objects.ClefValue
import com.trippntechnology.tnt.flashcards.objects.NoteValue
import com.trippntechnology.tnt.flashcards.util.view.BaseView

class SelectionView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    BaseView(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private val bassNotes = emptyList<SelectableNoteArea>().toMutableList()
    private val trebleNotes = emptyList<SelectableNoteArea>().toMutableList()

    private val staffSpacing = LINE_SPACING * 5.5f

    init {
        NoteValue.values().forEach {
            bassNotes.add(SelectableNoteArea(context, STROKE_WIDTH, ClefValue.BASS, it))
        }
        NoteValue.values().forEach {
            trebleNotes.add(SelectableNoteArea(context, STROKE_WIDTH, ClefValue.TREBLE, it))
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return
        val staffXCenter = width / 2f
        val bottomStaffYCenter = height / 2f + staffSpacing / 2 + LINE_SPACING * 2
        val topStaffYCenter = height / 2f - staffSpacing / 2 - LINE_SPACING * 2

        //Draw staff
        staffArea.bounds.top = bottomStaffYCenter - LINE_SPACING * 2
        staffArea.bounds.bottom = staffArea.bounds.top
        staffArea.bounds.left = 0f + padding
        staffArea.bounds.right = width.toFloat() - padding
        staffArea.draw(canvas)
        staffArea.bounds.top = topStaffYCenter - LINE_SPACING * 2
        staffArea.bounds.bottom = staffArea.bounds.top
        staffArea.draw(canvas)

        //Draw clef
        clefArea.bounds.left = 0f
        clefArea.bounds.right = clefArea.bounds.left + CLEF_WIDTH
        clefArea.bounds.top = (bottomStaffYCenter - BASS_HEIGHT / 2 - LINE_SPACING / 4)
        clefArea.bounds.bottom = clefArea.bounds.top + BASS_HEIGHT
        clefArea.clef = ClefValue.BASS
        clefArea.draw(canvas)
        clefArea.bounds.top = (topStaffYCenter - TREBLE_HEIGHT / 2 + STROKE_WIDTH)
        clefArea.bounds.bottom = clefArea.bounds.top + TREBLE_HEIGHT
        clefArea.clef = ClefValue.TREBLE
        clefArea.draw(canvas)

        //Draw note
//        noteArea.bounds.left = xOffset - NOTE_BASE_WIDTH / 2
//        noteArea.bounds.top = staffYCenter + noteYOffset - NOTE_BASE_HEIGHT / 2
//        noteArea.bounds.right = xOffset + NOTE_BASE_WIDTH / 2
//        noteArea.bounds.bottom = staffYCenter + noteYOffset + NOTE_BASE_HEIGHT / 2
//        canvas.save()
//        canvas.rotate(NOTE_BASE_ROTATION_ANGLE, xOffset, staffYCenter + noteYOffset)
//        noteArea.draw(canvas)
//        canvas.restore()



    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return true
    }
}