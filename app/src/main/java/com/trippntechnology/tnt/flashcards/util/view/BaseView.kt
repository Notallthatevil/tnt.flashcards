package com.trippntechnology.tnt.flashcards.util.view

import android.content.Context
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import com.trippntechnology.tnt.flashcards.R
import kotlin.math.min
import kotlin.math.roundToInt

abstract class BaseView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    //DRAWING
    protected var LINE_SPACING: Float
    protected var STROKE_SIZE: Float

    //NOTE POSITION
    protected var HALF_STEP_UP: Float
    protected var HALF_STEP_DOWN: Float
    protected var FULL_STEP_UP: Float
    protected var FULL_STEP_DOWN: Float
    protected val CENTER_STAFF = 0f

    //NOTE OVAL
    protected var NOTE_BASE_HEIGHT: Float
    protected var NOTE_BASE_WIDTH: Float
    protected val NOTE_BASE_ROTATION_ANGLE = 340f

    //NOTE HANDLE
    protected var NOTE_HANDLE_HEIGHT: Float
    protected var NOTE_HANDLE_WIDTH: Float
    protected var NOTE_HANDLE_CORNER_RADIUS: Float

    //CLEFS
    protected var TREBLE_HEIGHT: Int
    protected var BASS_HEIGHT: Int
    protected var CLEF_WIDTH: Int

    protected var noteBase: RectF
    protected var noteHandle: RectF

    protected val trebleDrawable = ContextCompat.getDrawable(context, R.drawable.treble)
    protected val bassDrawable = ContextCompat.getDrawable(context, R.drawable.bass)

    protected val blackPaint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.darkStroke)
        setShadowLayer(
            dpToPx(8f),
            0f,
            dpToPx(4f),
            ContextCompat.getColor(context, R.color.darkStrokeShadow)
        )
        isAntiAlias = true
    }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.BaseView,
            0, 0
        ).apply {

            try {
                LINE_SPACING = getDimension(R.styleable.BaseView_line_space, dpToPx(48f))
                STROKE_SIZE = getDimension(R.styleable.BaseView_stroke_width, dpToPx(4f))
            } finally {
                recycle()
            }
        }
        //NOTE POSITION
        HALF_STEP_UP = -LINE_SPACING / 2
        HALF_STEP_DOWN = LINE_SPACING / 2
        FULL_STEP_UP = -LINE_SPACING
        FULL_STEP_DOWN = LINE_SPACING
        //NOTE OVAL
        NOTE_BASE_HEIGHT = LINE_SPACING - STROKE_SIZE
        NOTE_BASE_WIDTH = LINE_SPACING * 1.5f - STROKE_SIZE
        //NOTE HANDLE
        NOTE_HANDLE_HEIGHT = LINE_SPACING * 4
        NOTE_HANDLE_WIDTH = STROKE_SIZE
        NOTE_HANDLE_CORNER_RADIUS = STROKE_SIZE
        //NOTE SHAPES
        noteBase = RectF(0f, 0f, NOTE_BASE_WIDTH, NOTE_BASE_HEIGHT)
        noteHandle = RectF(0f, 0f, NOTE_HANDLE_WIDTH, NOTE_HANDLE_HEIGHT)
        //CLEFS
        TREBLE_HEIGHT = (LINE_SPACING * 6).roundToInt()
        BASS_HEIGHT = (LINE_SPACING * 4).roundToInt()
        CLEF_WIDTH = (LINE_SPACING * 3.5).roundToInt()
        //CLEF SHAPES
        val trebleClefBounds = Rect(0, 0, CLEF_WIDTH, TREBLE_HEIGHT)
        val bassClefBounds = Rect(0, 0, CLEF_WIDTH, BASS_HEIGHT)

        trebleDrawable?.bounds = trebleClefBounds
        bassDrawable?.bounds = bassClefBounds

        blackPaint.strokeWidth = STROKE_SIZE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = CLEF_WIDTH * 2 + NOTE_BASE_WIDTH
        val desiredHeight = LINE_SPACING * 14+dpToPx(16f)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
//
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
        setMeasuredDimension(height, width)
    }


    protected fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            resources.displayMetrics
        )
    }

}