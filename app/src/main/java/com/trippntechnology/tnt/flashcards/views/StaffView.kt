package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import com.trippntechnology.tnt.flashcards.R


class StaffView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private val LINE_SPACING = dpToPx(48f)
    private val STAFF_STROKE_SIZE = dpToPx(4f)
    private val HALF_STEP_UP = -LINE_SPACING / 2
    private val HALF_STEP_DOWN = LINE_SPACING / 2
    private val FULL_STEP_UP = -LINE_SPACING
    private val FULL_STEP_DOWN = LINE_SPACING

    private val NOTE_BASE_HEIGHT = LINE_SPACING - STAFF_STROKE_SIZE
    private val NOTE_BASE_WIDTH = LINE_SPACING * 1.5f - STAFF_STROKE_SIZE
    private val NOTE_HANDLE_HEIGHT = LINE_SPACING * 4
    private val NOTE_HANDLE_WIDTH = STAFF_STROKE_SIZE
    private val NOTE_HANDLE_CORNER_RADIUS = dpToPx(4f)
    private val NOTE_BASE_ROTATION_ANGLE = 340f

    private val points = FloatArray(20)
    private val noteBase = RectF(0f, 0f, NOTE_BASE_WIDTH, NOTE_BASE_HEIGHT)
    private val noteHandle = RectF(0f, 0f,NOTE_HANDLE_WIDTH,NOTE_HANDLE_HEIGHT)

    val notePosition = 0f

    val paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        val centerWidth = width / 2f
        val centerHeight = height / 2f
        val startX = dpToPx(8f)
        val endX = width - dpToPx(8f)

        paint.color = ContextCompat.getColor(context, R.color.darkStroke)
        paint.setShadowLayer(
            dpToPx(8f),
            0f,
            dpToPx(4f),
            ContextCompat.getColor(context, R.color.darkStrokeShadow)
        )
        paint.strokeWidth = STAFF_STROKE_SIZE
        paint.isAntiAlias = true

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
        canvas.drawLines(points, paint)
        canvas.save()
        //Draw the note
        canvas.translate(centerWidth - NOTE_BASE_WIDTH / 2, centerHeight - NOTE_BASE_HEIGHT / 2)
        canvas.translate(0f, notePosition)
        canvas.rotate(NOTE_BASE_ROTATION_ANGLE, NOTE_BASE_WIDTH / 2, NOTE_BASE_HEIGHT / 2)
        canvas.drawOval(noteBase, paint)
        canvas.restore()
        paint.color = ContextCompat.getColor(context, R.color.colorAccentDark)
        canvas.translate(centerWidth+(NOTE_BASE_WIDTH/2-NOTE_HANDLE_WIDTH*1.5f),centerHeight-(NOTE_HANDLE_HEIGHT+NOTE_BASE_HEIGHT/8))
        canvas.drawRoundRect(noteHandle,NOTE_HANDLE_CORNER_RADIUS,NOTE_HANDLE_CORNER_RADIUS,paint)


    }

    private fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        )
    }

}