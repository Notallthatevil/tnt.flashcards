package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.objects.NoteValue
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.min
import kotlin.math.roundToInt


class StaffView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private var LINE_SPACING = dpToPx(48f)
    private var STAFF_STROKE_SIZE = dpToPx(4f)

    private val HALF_STEP_UP = -LINE_SPACING / 2
    private val HALF_STEP_DOWN = LINE_SPACING / 2
    private val FULL_STEP_UP = -LINE_SPACING
    private val FULL_STEP_DOWN = LINE_SPACING
    private val CENTER_STAFF = 0f

    private val NOTE_BASE_HEIGHT = LINE_SPACING - STAFF_STROKE_SIZE
    private val NOTE_BASE_WIDTH = LINE_SPACING * 1.5f - STAFF_STROKE_SIZE
    private val NOTE_BASE_ROTATION_ANGLE = 340f

    private val NOTE_HANDLE_HEIGHT = LINE_SPACING * 4
    private val NOTE_HANDLE_WIDTH = STAFF_STROKE_SIZE
    private val NOTE_HANDLE_CORNER_RADIUS = dpToPx(4f)

    private val TREBLE_HEIGHT = (LINE_SPACING * 6).roundToInt()
    private val BASS_HEIGHT = (LINE_SPACING * 4).roundToInt()
    private val CLEF_WIDTH = (LINE_SPACING * 3.5).roundToInt()

    private val points = FloatArray(20)
    private val noteBase = RectF(0f, 0f, NOTE_BASE_WIDTH, NOTE_BASE_HEIGHT)
    private val noteHandle = RectF(0f, 0f, NOTE_HANDLE_WIDTH, NOTE_HANDLE_HEIGHT)

    private var noteTranslation = FULL_STEP_DOWN * 6 + HALF_STEP_DOWN
    private var clef = TREBLE_CLEF

    private val blackPaint = Paint()

    private val trebleDrawable = ContextCompat.getDrawable(context, R.drawable.treble)
    private val bassDrawable = ContextCompat.getDrawable(context, R.drawable.bass)
    private val trebleClefBounds = Rect(0, 0, this.CLEF_WIDTH, TREBLE_HEIGHT)
    private val bassClefBounds = Rect(0, 0, this.CLEF_WIDTH, BASS_HEIGHT)

    init {
        blackPaint.color = ContextCompat.getColor(context, R.color.darkStroke)
        blackPaint.setShadowLayer(
            dpToPx(8f),
            0f,
            dpToPx(4f),
            ContextCompat.getColor(context, R.color.darkStrokeShadow)
        )
        blackPaint.strokeWidth = STAFF_STROKE_SIZE
        blackPaint.isAntiAlias = true

        trebleDrawable?.bounds = trebleClefBounds
        bassDrawable?.bounds = bassClefBounds

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = CLEF_WIDTH * 2 + NOTE_BASE_WIDTH
        val desiredHeight = LINE_SPACING * 14+dpToPx(16f)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val height: Int
        val width: Int

        width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> min(widthSize, desiredWidth.roundToInt())
            MeasureSpec.UNSPECIFIED -> desiredWidth.roundToInt()
            else -> throw UnsupportedOperationException("Width didn't receive a value")
        }
        height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> min(heightSize, desiredHeight.roundToInt())
            MeasureSpec.UNSPECIFIED -> desiredHeight.roundToInt()
            else -> throw UnsupportedOperationException("Height didn't receive a value")
        }
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return
        //Determine basic positioning
        val centerWidth = width / 2f
        val centerHeight = height / 2f
        val startX = dpToPx(8f)
        val endX = width - dpToPx(8f)

        //Create staff lines and draw
        var top = centerHeight - LINE_SPACING * 2
        var pass = 0
        for (i in 0 until points.size) {
            if (i % 4 == 0 || i == 0) {
                points[i] = startX
            } else if (i % 2 == 0) {
                points[i] = endX
            } else if (i % 2 != 0) {
                points[i] = top
                pass++
                if (pass == 2) {
                    pass = 0
                    top += LINE_SPACING
                }
            }
        }
        canvas.drawLines(points, blackPaint)
        canvas.save()
        if (clef == TREBLE_CLEF) {
            canvas.translate(0f, centerHeight - TREBLE_HEIGHT / 2)
            trebleDrawable?.draw(canvas)
        } else if (clef == BASS_CLEF) {
            canvas.translate(
                dpToPx(8f),
                centerHeight - BASS_HEIGHT / 2 - LINE_SPACING / 2 + STAFF_STROKE_SIZE * 2
            )
            bassDrawable?.draw(canvas)
        }
        canvas.restore()
        canvas.save()


        //Draw the note
        canvas.translate(
            centerWidth - NOTE_BASE_WIDTH / 2 + CLEF_WIDTH * .6f,
            centerHeight - NOTE_BASE_HEIGHT / 2
        )
        canvas.translate(0f, noteTranslation)
        canvas.rotate(NOTE_BASE_ROTATION_ANGLE, NOTE_BASE_WIDTH / 2, NOTE_BASE_HEIGHT / 2)
        canvas.drawOval(noteBase, blackPaint)
        canvas.restore()

        //Determine if extra lines need to be drawn and then draw
        val extraStartX = centerWidth - NOTE_BASE_WIDTH / 2 + CLEF_WIDTH * .6f
        val extraEndX = centerWidth + NOTE_BASE_WIDTH / 2 + CLEF_WIDTH * .6f
        if (noteTranslation < FULL_STEP_UP * 2) {
            val numberOfLines = abs((noteTranslation / FULL_STEP_UP) - 2)

            for (i in 0 until floor(numberOfLines).toInt()) {
                canvas.drawLine(
                    extraStartX,
                    getExtraLinePosition(i, centerHeight, PLACE_ABOVE),
                    extraEndX,
                    getExtraLinePosition(i, centerHeight, PLACE_ABOVE),
                    blackPaint
                )
            }
        } else if (noteTranslation > FULL_STEP_DOWN * 2) {
            val numberOfLines = (noteTranslation / FULL_STEP_DOWN) - 2

            for (i in 0 until floor(numberOfLines).toInt()) {
                canvas.drawLine(
                    extraStartX,
                    getExtraLinePosition(i, centerHeight, PLACE_BELOW),
                    extraEndX,
                    getExtraLinePosition(i, centerHeight, PLACE_BELOW),
                    blackPaint
                )
            }
        }

        //Draw the handle
        if (noteTranslation < 0) {
            canvas.translate(
                centerWidth - (NOTE_BASE_WIDTH / 2 - NOTE_HANDLE_WIDTH * .2f) + CLEF_WIDTH * .6f,
                centerHeight + (NOTE_BASE_HEIGHT / 8)
            )
        } else {
            canvas.translate(
                centerWidth + (NOTE_BASE_WIDTH / 2 - NOTE_HANDLE_WIDTH * 1.5f) + CLEF_WIDTH * .6f,
                centerHeight - (NOTE_HANDLE_HEIGHT + NOTE_BASE_HEIGHT / 8)
            )
        }
        canvas.translate(0f, noteTranslation)
        canvas.drawRoundRect(
            noteHandle,
            NOTE_HANDLE_CORNER_RADIUS,
            NOTE_HANDLE_CORNER_RADIUS,
            blackPaint
        )

    }

    private fun getExtraLinePosition(multiplier: Int, offset: Float, translation: Int): Float {
        return when (translation) {
            PLACE_BELOW -> offset + LINE_SPACING * (3 + multiplier)
            PLACE_ABOVE -> offset - LINE_SPACING * (3 + multiplier)
            else -> throw IllegalArgumentException("EXPECTING PLACE_ABOVE OR PLACE_BELOW, INVALID ARGUMENT \"translation\"")
        }
    }

    fun setNote(clef: Int, note: NoteValue) {
        when (clef) {
            TREBLE_CLEF -> when (note) {
                NoteValue.X_LOW_C -> noteTranslation = FULL_STEP_DOWN * 9 + HALF_STEP_DOWN
                NoteValue.X_LOW_D -> noteTranslation = FULL_STEP_DOWN * 9
                NoteValue.X_LOW_E -> noteTranslation = FULL_STEP_DOWN * 8 + HALF_STEP_DOWN
                NoteValue.X_LOW_F -> noteTranslation = FULL_STEP_DOWN * 8
                NoteValue.X_LOW_G -> noteTranslation = FULL_STEP_DOWN * 7 + HALF_STEP_DOWN
                NoteValue.LOW_A -> noteTranslation = FULL_STEP_DOWN * 7
                NoteValue.LOW_B -> noteTranslation = FULL_STEP_DOWN * 6 + HALF_STEP_DOWN
                NoteValue.LOW_C -> noteTranslation = FULL_STEP_DOWN * 6
                NoteValue.LOW_D -> noteTranslation = FULL_STEP_DOWN * 5 + HALF_STEP_DOWN
                NoteValue.LOW_E -> noteTranslation = FULL_STEP_DOWN * 5
                NoteValue.LOW_F -> noteTranslation = FULL_STEP_DOWN * 4 + HALF_STEP_DOWN
                NoteValue.MIDDLE_A -> noteTranslation = FULL_STEP_DOWN * 4
                NoteValue.MIDDLE_B -> noteTranslation = FULL_STEP_DOWN * 3 + HALF_STEP_DOWN
                NoteValue.MIDDLE_C -> noteTranslation = FULL_STEP_DOWN * 3
                NoteValue.MIDDLE_D -> noteTranslation = FULL_STEP_DOWN * 2 + HALF_STEP_DOWN
                NoteValue.MIDDLE_E -> noteTranslation = FULL_STEP_DOWN * 2
                NoteValue.MIDDLE_F -> noteTranslation = FULL_STEP_DOWN + HALF_STEP_DOWN
                NoteValue.MIDDLE_G -> noteTranslation = FULL_STEP_DOWN
                NoteValue.HIGH_A -> noteTranslation = HALF_STEP_DOWN
                NoteValue.HIGH_B -> noteTranslation = CENTER_STAFF
                NoteValue.HIGH_C -> noteTranslation = HALF_STEP_UP
                NoteValue.HIGH_D -> noteTranslation = FULL_STEP_UP
                NoteValue.HIGH_E -> noteTranslation = FULL_STEP_UP + HALF_STEP_UP
                NoteValue.HIGH_F -> noteTranslation = FULL_STEP_UP * 2
                NoteValue.HIGH_G -> noteTranslation = FULL_STEP_UP * 2 + HALF_STEP_UP
                NoteValue.X_HIGH_A -> noteTranslation = FULL_STEP_UP * 3
                NoteValue.X_HIGH_B -> noteTranslation = FULL_STEP_UP * 3 + HALF_STEP_UP
                NoteValue.X_HIGH_C -> noteTranslation = FULL_STEP_UP * 4
            }
            BASS_CLEF -> when (note) {
                NoteValue.X_LOW_C -> noteTranslation = FULL_STEP_DOWN * 4
                NoteValue.X_LOW_D -> noteTranslation = FULL_STEP_DOWN * 3 + HALF_STEP_DOWN
                NoteValue.X_LOW_E -> noteTranslation = FULL_STEP_DOWN * 3
                NoteValue.X_LOW_F -> noteTranslation = FULL_STEP_DOWN * 2 + HALF_STEP_DOWN
                NoteValue.X_LOW_G -> noteTranslation = FULL_STEP_DOWN * 2
                NoteValue.LOW_A -> noteTranslation = FULL_STEP_DOWN + HALF_STEP_DOWN
                NoteValue.LOW_B -> noteTranslation = FULL_STEP_DOWN
                NoteValue.LOW_C -> noteTranslation = HALF_STEP_DOWN
                NoteValue.LOW_D -> noteTranslation = CENTER_STAFF
                NoteValue.LOW_E -> noteTranslation = HALF_STEP_UP
                NoteValue.LOW_F -> noteTranslation = FULL_STEP_UP
                NoteValue.MIDDLE_A -> noteTranslation = FULL_STEP_UP + HALF_STEP_UP
                NoteValue.MIDDLE_B -> noteTranslation = FULL_STEP_UP * 2
                NoteValue.MIDDLE_C -> noteTranslation = FULL_STEP_UP * 2 + HALF_STEP_UP
                NoteValue.MIDDLE_D -> noteTranslation = FULL_STEP_UP * 3
                NoteValue.MIDDLE_E -> noteTranslation = FULL_STEP_UP * 3 + HALF_STEP_UP
                NoteValue.MIDDLE_F -> noteTranslation = FULL_STEP_UP * 4
                NoteValue.MIDDLE_G -> noteTranslation = FULL_STEP_UP * 4 + HALF_STEP_UP
                NoteValue.HIGH_A -> noteTranslation = FULL_STEP_UP * 5
                NoteValue.HIGH_B -> noteTranslation = FULL_STEP_UP * 5 + HALF_STEP_UP
                NoteValue.HIGH_C -> noteTranslation = FULL_STEP_UP * 6
                NoteValue.HIGH_D -> noteTranslation = FULL_STEP_UP * 6 + HALF_STEP_UP
                NoteValue.HIGH_E -> noteTranslation = FULL_STEP_UP * 7
                NoteValue.HIGH_F -> noteTranslation = FULL_STEP_UP * 7 + HALF_STEP_UP
                NoteValue.HIGH_G -> noteTranslation = FULL_STEP_UP * 8
                NoteValue.X_HIGH_A -> noteTranslation = FULL_STEP_UP * 8 + HALF_STEP_UP
                NoteValue.X_HIGH_B -> noteTranslation = FULL_STEP_UP * 9
                NoteValue.X_HIGH_C -> noteTranslation = FULL_STEP_UP * 9 + HALF_STEP_UP

            }
            else -> throw IllegalArgumentException("clef must equal TREBLE_CLEF or BASS_CLEF")
        }
        this.clef = clef
        invalidate()
    }

    private fun setLineSize(sizeInDp: Float) {
        LINE_SPACING = dpToPx(sizeInDp)
        invalidate()
    }

    private fun setLineWidth(widthInDp: Float) {
        STAFF_STROKE_SIZE = dpToPx(widthInDp)
        invalidate()
    }


    private fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        )
    }

    companion object {
        const val TREBLE_CLEF = 100
        const val BASS_CLEF = 200

        const val PLACE_ABOVE = 10
        const val PLACE_BELOW = 20
    }
}