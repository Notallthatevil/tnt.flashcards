package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import com.trippntechnology.tnt.flashcards.R
import com.trippntechnology.tnt.flashcards.objects.ClefValue
import com.trippntechnology.tnt.flashcards.objects.NoteValue

class SelectableNoteArea(context: Context, strokeWidth: Float,clef: ClefValue,note:NoteValue) :
    FlashCardArea(context, strokeWidth){

    private var selected: Boolean = false

    private val selectedPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.selectedStroke)
        this.strokeWidth = this@SelectableNoteArea.strokeWidth
        setShadowLayer(
            dpToPx(8f),
            0f,
            dpToPx(4f),
            ContextCompat.getColor(context, R.color.selectedStrokeShadow)
        )
    }


    override fun draw(canvas: Canvas) {
        if (selected) {
            canvas.drawOval(bounds, selectedPaint)
        } else {
            canvas.drawOval(bounds, blackPaint)
        }
    }

    fun selected(){
        selected = !selected
    }

}