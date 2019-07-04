package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.RectF
import android.util.TypedValue

abstract class Area(protected val context:Context) {

    protected val bounds = RectF()

    protected abstract fun draw(canvas: Canvas)

    protected fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        )
    }
}