package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.trippntechnology.tnt.flashcards.R
import kotlin.math.roundToInt

abstract class FlashCardArea(
    context: Context,private val strokeWidth:Float
) : Area(context) {

    protected val blackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.darkStroke)
        strokeWidth = this@FlashCardArea.strokeWidth
        setShadowLayer(
            dpToPx(8f),
            0f,
            dpToPx(4f),
            ContextCompat.getColor(context, R.color.darkStrokeShadow)
        )
    }

}