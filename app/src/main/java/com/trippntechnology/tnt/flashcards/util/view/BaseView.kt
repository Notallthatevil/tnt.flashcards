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
import com.trippntechnology.tnt.flashcards.views.*
import kotlin.math.min
import kotlin.math.roundToInt

abstract class BaseView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    protected val clefArea = ClefArea(context, attrs)
    protected val staffArea = StaffArea(context, attrs)
    protected val noteArea = NoteArea(context, attrs)
    protected val ledgerLineArea = LedgerLineArea(context, attrs)
    protected val stemArea = StemArea(context, attrs)





    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = clefArea.CLEF_WIDTH * 2 + noteArea.NOTE_BASE_WIDTH
        val desiredHeight = staffArea.LINE_SPACING * 14 + dpToPx(16f)
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