package com.trippntechnology.tnt.flashcards.util.view

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.views.*
import kotlin.math.min
import kotlin.math.roundToInt

abstract class BaseView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    protected val LINE_SPACING: Float

    protected val STROKE_WIDTH: Float

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.BaseView,
            0, 0
        ).apply {
            LINE_SPACING = getDimension(
                R.styleable.BaseView_line_space,
                resources.getDimension(R.dimen.default_line_space)
            )
            STROKE_WIDTH = getDimension(
                R.styleable.BaseView_stroke_width,
                resources.getDimension(R.dimen.default_stroke_width)
            )
        }

    }

    //NOTE POSITION
    protected val HALF_STEP_UP = -LINE_SPACING / 2
    protected val HALF_STEP_DOWN = LINE_SPACING / 2
    protected val FULL_STEP_UP = -LINE_SPACING
    protected val FULL_STEP_DOWN = LINE_SPACING
    protected val CENTER_STAFF = 0f
    //Note size
    protected val NOTE_BASE_HEIGHT = LINE_SPACING - STROKE_WIDTH
    protected val NOTE_BASE_WIDTH = LINE_SPACING * 1.5f - STROKE_WIDTH

    protected val NOTE_STEM_HEIGHT = LINE_SPACING * 4
    protected val NOTE_STEM_WIDTH = STROKE_WIDTH

    //CLEFS
    protected val TREBLE_HEIGHT = (LINE_SPACING * 6)
    protected val BASS_HEIGHT = (LINE_SPACING * 4)
    protected val CLEF_WIDTH = (LINE_SPACING * 3.5f)

    protected val clefArea = ClefArea(context, STROKE_WIDTH)
    protected val staffArea = StaffArea(context, STROKE_WIDTH, LINE_SPACING)
    protected val noteArea = NoteArea(context, STROKE_WIDTH,LINE_SPACING)
    protected val ledgerLineArea = LedgerLineArea(context, STROKE_WIDTH)
    protected val stemArea = StemArea(context, STROKE_WIDTH)

    protected val padding = dpToPx(2f)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = CLEF_WIDTH * 2 + NOTE_BASE_WIDTH
        val desiredHeight = LINE_SPACING * 14 + dpToPx(16f)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val width = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> min(widthSize, desiredWidth.roundToInt())
            MeasureSpec.UNSPECIFIED -> desiredWidth.roundToInt()
            else -> throw UnsupportedOperationException("Width didn't receive a value")
        }
        val height = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> min(heightSize, desiredHeight.roundToInt())
            MeasureSpec.UNSPECIFIED -> desiredHeight.roundToInt()
            else -> throw UnsupportedOperationException("Height didn't receive a value")
        }
        setMeasuredDimension(width, height)
    }

    protected fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            resources.displayMetrics
        )
    }

}