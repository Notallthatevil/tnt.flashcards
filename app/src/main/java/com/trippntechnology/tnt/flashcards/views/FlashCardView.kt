package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.objects.NoteValue
import com.trippntechnology.tnt.flashcards.util.view.BaseView
import kotlin.math.floor


class FlashCardView
    (context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    BaseView(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)


    private var clef = ClefArea.Clef.BASS

    val fullStepDown = FULL_STEP_DOWN
    val fullStepUp = FULL_STEP_UP
    val halfStepDown = HALF_STEP_DOWN
    val halfStepUp = HALF_STEP_UP
    val centerStaff = CENTER_STAFF

    /**
     * Just enough to give the note some space between the clef and the edge
     */
    private var noteXOffset = LINE_SPACING

    private var noteYOffset = centerStaff

    private val padding = dpToPx(2f)


    override fun onDraw(canvas: Canvas?) {
        canvas ?: return
        val staffYCenter = height / 2f
        val widthCenter = width / 2f
        val xOffset = widthCenter + noteXOffset

        //Draw staff
        staffArea.bounds.top = staffYCenter - LINE_SPACING * 2
        staffArea.bounds.bottom = staffArea.bounds.top
        staffArea.bounds.left = 0f+padding
        staffArea.bounds.right = width.toFloat()-padding
        staffArea.draw(canvas)

        //Draw clef
        clefArea.clef = clef
        clefArea.bounds.left = 0f
        clefArea.bounds.right = clefArea.bounds.left + CLEF_WIDTH
        if (clef == ClefArea.Clef.BASS) {
            clefArea.bounds.top = (staffYCenter - BASS_HEIGHT / 2 - LINE_SPACING / 4)
            clefArea.bounds.bottom = clefArea.bounds.top + BASS_HEIGHT
        } else {
            clefArea.bounds.top = (staffYCenter - TREBLE_HEIGHT / 2 + STROKE_WIDTH)
            clefArea.bounds.bottom = clefArea.bounds.top + TREBLE_HEIGHT
        }
        clefArea.draw(canvas)

        //Draw note
        noteArea.bounds.left = xOffset - NOTE_BASE_WIDTH / 2
        noteArea.bounds.top = staffYCenter + noteYOffset - NOTE_BASE_HEIGHT / 2
        noteArea.bounds.right = xOffset + NOTE_BASE_WIDTH / 2
        noteArea.bounds.bottom = staffYCenter + noteYOffset + NOTE_BASE_HEIGHT / 2
        canvas.save()
        canvas.rotate(NOTE_BASE_ROTATION_ANGLE,xOffset,staffYCenter + noteYOffset)
        noteArea.draw(canvas)
        canvas.restore()

        //Draw stem
        if (staffYCenter + noteYOffset < staffYCenter) {
            stemArea.bounds.left = xOffset - NOTE_BASE_WIDTH / 2+STROKE_WIDTH/4
            stemArea.bounds.top = staffYCenter + noteYOffset+STROKE_WIDTH
            stemArea.bounds.right = stemArea.bounds.left + NOTE_STEM_WIDTH
            stemArea.bounds.bottom = stemArea.bounds.top + NOTE_STEM_HEIGHT
        } else {
            stemArea.bounds.left = xOffset + NOTE_BASE_WIDTH / 2-STROKE_WIDTH*1.25f
            stemArea.bounds.bottom = staffYCenter + noteYOffset-STROKE_WIDTH
            stemArea.bounds.right = stemArea.bounds.left + NOTE_STEM_WIDTH
            stemArea.bounds.top = stemArea.bounds.bottom-NOTE_STEM_HEIGHT
        }
        stemArea.draw(canvas)

        //Draw ledger lines
        ledgerLineArea.bounds.left = xOffset - NOTE_BASE_WIDTH / 2
        ledgerLineArea.bounds.right = xOffset + NOTE_BASE_WIDTH / 2
        var ledgerLine = 0
        val offsetBelow = staffYCenter + LINE_SPACING * 3
        var noteOffset = staffYCenter + noteYOffset
        while (floor(noteOffset) >= floor(offsetBelow)) {
            ledgerLineArea.bounds.top = offsetBelow + LINE_SPACING * ledgerLine
            ledgerLineArea.bounds.bottom =  ledgerLineArea.bounds.top
            noteOffset -= LINE_SPACING
            ledgerLine++
            ledgerLineArea.draw(canvas)
        }
        val offsetAbove = staffYCenter - LINE_SPACING * 3
        while (floor(noteOffset) <= floor(offsetAbove)) {
            ledgerLineArea.bounds.top = offsetAbove - LINE_SPACING * ledgerLine
            ledgerLineArea.bounds.bottom =  ledgerLineArea.bounds.top
            noteOffset += LINE_SPACING
            ledgerLine++
            ledgerLineArea.draw(canvas)
        }
    }


    fun setNote(clef: ClefArea.Clef, note: NoteValue) {
        when (clef) {
            ClefArea.Clef.TREBLE -> when (note) {
                NoteValue.X_LOW_C -> noteYOffset = fullStepDown * 9 + halfStepDown
                NoteValue.X_LOW_D -> noteYOffset = fullStepDown * 9
                NoteValue.X_LOW_E -> noteYOffset = fullStepDown * 8 + halfStepDown
                NoteValue.X_LOW_F -> noteYOffset = fullStepDown * 8
                NoteValue.X_LOW_G -> noteYOffset = fullStepDown * 7 + halfStepDown
                NoteValue.LOW_A -> noteYOffset = fullStepDown * 7
                NoteValue.LOW_B -> noteYOffset = fullStepDown * 6 + halfStepDown
                NoteValue.LOW_C -> noteYOffset = fullStepDown * 6
                NoteValue.LOW_D -> noteYOffset = fullStepDown * 5 + halfStepDown
                NoteValue.LOW_E -> noteYOffset = fullStepDown * 5
                NoteValue.LOW_F -> noteYOffset = fullStepDown * 4 + halfStepDown
                NoteValue.MIDDLE_A -> noteYOffset = fullStepDown * 4
                NoteValue.MIDDLE_B -> noteYOffset = fullStepDown * 3 + halfStepDown
                NoteValue.MIDDLE_C -> noteYOffset = fullStepDown * 3
                NoteValue.MIDDLE_D -> noteYOffset = fullStepDown * 2 + halfStepDown
                NoteValue.MIDDLE_E -> noteYOffset = fullStepDown * 2
                NoteValue.MIDDLE_F -> noteYOffset = fullStepDown + halfStepDown
                NoteValue.MIDDLE_G -> noteYOffset = fullStepDown
                NoteValue.HIGH_A -> noteYOffset = halfStepDown
                NoteValue.HIGH_B -> noteYOffset = centerStaff
                NoteValue.HIGH_C -> noteYOffset = halfStepUp
                NoteValue.HIGH_D -> noteYOffset = fullStepUp
                NoteValue.HIGH_E -> noteYOffset = fullStepUp + halfStepUp
                NoteValue.HIGH_F -> noteYOffset = fullStepUp * 2
                NoteValue.HIGH_G -> noteYOffset = fullStepUp * 2 + halfStepUp
                NoteValue.X_HIGH_A -> noteYOffset = fullStepUp * 3
                NoteValue.X_HIGH_B -> noteYOffset = fullStepUp * 3 + halfStepUp
                NoteValue.X_HIGH_C -> noteYOffset = fullStepUp * 4
            }
            ClefArea.Clef.BASS -> when (note) {
                NoteValue.X_LOW_C -> noteYOffset = fullStepDown * 4
                NoteValue.X_LOW_D -> noteYOffset = fullStepDown * 3 + halfStepDown
                NoteValue.X_LOW_E -> noteYOffset = fullStepDown * 3
                NoteValue.X_LOW_F -> noteYOffset = fullStepDown * 2 + halfStepDown
                NoteValue.X_LOW_G -> noteYOffset = fullStepDown * 2
                NoteValue.LOW_A -> noteYOffset = fullStepDown + halfStepDown
                NoteValue.LOW_B -> noteYOffset = fullStepDown
                NoteValue.LOW_C -> noteYOffset = halfStepDown
                NoteValue.LOW_D -> noteYOffset = centerStaff
                NoteValue.LOW_E -> noteYOffset = halfStepUp
                NoteValue.LOW_F -> noteYOffset = fullStepUp
                NoteValue.MIDDLE_A -> noteYOffset = fullStepUp + halfStepUp
                NoteValue.MIDDLE_B -> noteYOffset = fullStepUp * 2
                NoteValue.MIDDLE_C -> noteYOffset = fullStepUp * 2 + halfStepUp
                NoteValue.MIDDLE_D -> noteYOffset = fullStepUp * 3
                NoteValue.MIDDLE_E -> noteYOffset = fullStepUp * 3 + halfStepUp
                NoteValue.MIDDLE_F -> noteYOffset = fullStepUp * 4
                NoteValue.MIDDLE_G -> noteYOffset = fullStepUp * 4 + halfStepUp
                NoteValue.HIGH_A -> noteYOffset = fullStepUp * 5
                NoteValue.HIGH_B -> noteYOffset = fullStepUp * 5 + halfStepUp
                NoteValue.HIGH_C -> noteYOffset = fullStepUp * 6
                NoteValue.HIGH_D -> noteYOffset = fullStepUp * 6 + halfStepUp
                NoteValue.HIGH_E -> noteYOffset = fullStepUp * 7
                NoteValue.HIGH_F -> noteYOffset = fullStepUp * 7 + halfStepUp
                NoteValue.HIGH_G -> noteYOffset = fullStepUp * 8
                NoteValue.X_HIGH_A -> noteYOffset = fullStepUp * 8 + halfStepUp
                NoteValue.X_HIGH_B -> noteYOffset = fullStepUp * 9
                NoteValue.X_HIGH_C -> noteYOffset = fullStepUp * 9 + halfStepUp

            }
            else -> throw IllegalArgumentException("clef must equal TREBLE_CLEF or BASS_CLEF")
        }
        this.clef = clef
        invalidate()
    }

}