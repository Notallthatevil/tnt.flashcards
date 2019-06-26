package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
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
    private val points = FloatArray(20)

    val paint = Paint()
    val rect = Rect()

    init {

    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return
        val centerWidth = width / 2f
        val centerHeigth = height / 2f
        val startX = dpToPx(8f)
        val endX = width - dpToPx(8f)

        paint.color = (ContextCompat.getColor(context, R.color.darkStroke))
        paint.setShadowLayer(
            dpToPx(8f),
            0f,
            dpToPx(4f),
            ContextCompat.getColor(context, R.color.darkStrokeShadow)
        )
        paint.strokeWidth = dpToPx(4f)
        paint.isAntiAlias = true


        var top = centerHeigth-LINE_SPACING*2
        var pass = 0
        for (i in 0 until points.size) {
            if(i % 4 == 0 || i == 0){
                points[i] = startX
            }else if(i%2 ==0){
                points[i] = endX
            }else if(i%2 !=0){
                points[i] = top
                pass++
                if (pass == 2){
                    pass = 0
                    top += LINE_SPACING
                }
            }
        }
        canvas.drawLines(points,paint)
    }

    private fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        )
    }

}