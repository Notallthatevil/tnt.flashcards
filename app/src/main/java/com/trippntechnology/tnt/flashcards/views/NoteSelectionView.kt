package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.util.view.BaseView

class NoteSelectionView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    BaseView(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)


    private val CLEF_SEPARATOR_SPACE = LINE_SPACING * 4
    private val STAFF_PADDING = dpToPx(8f)

    //NUMBER OF HALF STEPS BELOW THE MIDDLE LINE ON THE STAFF
    private val LOWEST_BASS_NOTE_OFFSET = 8

    private val NUMBER_BASS_NOTES = 17

    private val points = FloatArray(80)

    private val selectedPaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.selectedStroke)
        setShadowLayer(
            dpToPx(8f),
            0f,
            dpToPx(4f),
            ContextCompat.getColor(context, R.color.selectedStrokeShadow)
        )
        isAntiAlias = true
        strokeWidth = STROKE_SIZE
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        val defaultCanvas = canvas.save()

        val centerWidth = width / 2f
        val centerHeight = height / 2f
        val startX = dpToPx(8f)
        val endX = width - dpToPx(8f)

        val upperStaffMidLineTop =
            centerHeight - (CLEF_SEPARATOR_SPACE*1.75f)-(LINE_SPACING*6)
        val lowerStaffMidLineTop =
            centerHeight - CLEF_SEPARATOR_SPACE * .75f - LINE_SPACING * 2

        val upperStaffMidLineBottom =
            centerHeight + CLEF_SEPARATOR_SPACE * .75f + LINE_SPACING * 2
        val lowerStaffMidLineBottom =
            centerHeight + (CLEF_SEPARATOR_SPACE*1.75f)+(LINE_SPACING*6)

        //Draw lines
        canvas.drawLine(startX, centerHeight, endX, centerHeight, selectedPaint)
        var yPos = upperStaffMidLineTop-LINE_SPACING*2
        var pass = 0
        for (i in 0 until points.size) {
            when (i) {
                20 -> yPos = upperStaffMidLineBottom - LINE_SPACING * 2
                40 -> yPos = lowerStaffMidLineTop - LINE_SPACING * 2
                60 -> yPos = lowerStaffMidLineBottom - LINE_SPACING * 2
            }
            if (i % 4 == 0 || i == 0) {
                points[i] = startX
            } else if (i % 2 == 0) {
                points[i] = endX
            } else if (i % 2 != 0) {
                points[i] = yPos
                pass++
                if (pass == 2) {
                    pass = 0
                    yPos += LINE_SPACING
                }
            }
        }
        canvas.drawLines(points, blackPaint)
        canvas.save()

        //Draw clefs
        canvas.translate(0f, upperStaffMidLineTop - TREBLE_HEIGHT / 2)
        trebleDrawable?.draw(canvas)
        canvas.restore()
        canvas.save()
        canvas.translate(
            dpToPx(8f),
            lowerStaffMidLineTop - BASS_HEIGHT / 2 - LINE_SPACING / 2 + STROKE_SIZE * 4
        )
        bassDrawable?.draw(canvas)
        canvas.restore()
        canvas.save()
        canvas.translate(0f, upperStaffMidLineBottom - TREBLE_HEIGHT / 2)
        trebleDrawable?.draw(canvas)
        canvas.restore()
        canvas.save()
        canvas.translate(
            dpToPx(8f),
            lowerStaffMidLineBottom - BASS_HEIGHT / 2 - LINE_SPACING / 2 + STROKE_SIZE * 4
        )
        bassDrawable?.draw(canvas)
        canvas.restore()
        canvas.save()

        val staffWidth = width - CLEF_WIDTH - STAFF_PADDING
        val bassNoteSeparation: Float = ((staffWidth / NUMBER_BASS_NOTES))
        val offset = HALF_STEP_DOWN * LOWEST_BASS_NOTE_OFFSET

        //Lower staff starting position
        canvas.translate(CLEF_WIDTH.toFloat(), lowerStaffMidLineTop)
        canvas.translate(0f, offset)

        for (i in 0 until NUMBER_BASS_NOTES) {
            val priorRotate = canvas.save()
            canvas.rotate(NOTE_BASE_ROTATION_ANGLE, NOTE_BASE_WIDTH / 2, NOTE_BASE_HEIGHT / 2)
            canvas.drawOval(noteBase, blackPaint)
            canvas.restoreToCount(priorRotate)
            canvas.translate(bassNoteSeparation, HALF_STEP_UP)
        }
    }
}