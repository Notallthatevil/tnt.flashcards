package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.trippntechnology.tnt.flashcards.R
import kotlin.math.roundToInt

abstract class FlashCardArea(
    context: Context,
    private val attrs: AttributeSet?
) : Area(context) {


    val LINE_SPACING:Float
        get() {
            context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.BaseView,
                0, 0
            ).apply {
                return getDimension(R.styleable.BaseView_line_space, dpToPx(48f))
            }
        }
    protected val STROKE_WIDTH  :Float
        get() {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.BaseView,
            0, 0
        ).apply {
            return getDimension(R.styleable.BaseView_stroke_width, dpToPx(4f))
        }
    }

    val STAFF_PADDING:Float
        get() {
            context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.BaseView,
                0, 0
            ).apply {
                return getDimension(R.styleable.BaseView_staff_padding, dpToPx(4f))
            }
        }

    val NOTE_BASE_HEIGHT = LINE_SPACING - STROKE_WIDTH
    val NOTE_BASE_WIDTH = LINE_SPACING * 1.5f - STROKE_WIDTH
    val NOTE_BASE_ROTATION_ANGLE = 340f

    protected val blackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.darkStroke)
        strokeWidth = STROKE_WIDTH
        setShadowLayer(
            dpToPx(8f),
            0f,
            dpToPx(4f),
            ContextCompat.getColor(context, R.color.darkStrokeShadow)
        )
    }

}