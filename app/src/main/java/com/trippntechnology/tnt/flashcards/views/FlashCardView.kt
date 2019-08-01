package com.trippntechnology.tnt.flashcards.views

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.lifecycle.MutableLiveData
import com.trippntechnology.tnt.androidbaseutils.ux.livedata.SingleLiveEvent
import com.trippntechnology.tnt.flashcards.objects.cardpacket.CardPacket
import com.trippntechnology.tnt.flashcards.objects.enums.clefvalue.ClefValue
import com.trippntechnology.tnt.flashcards.objects.enums.notevalue.NoteValue
import com.trippntechnology.tnt.flashcards.objects.note.Note
import com.trippntechnology.tnt.flashcards.util.view.BaseView
import timber.log.Timber
import java.util.*

class FlashCardView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
    BaseView(context, attrs, defStyleAttr) {
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)


    val cardChanged = MutableLiveData<Note>()
    val finishedList = SingleLiveEvent<Void>()

    private var currentNote = DEFAULT_NOTE
    private set(value) {
        field = value
        noteYOffset = Note.getNoteTranslation(field, LINE_SPACING)
        this.clef = field.clef
        invalidate()
        cardChanged.value = currentNote
    }

    private var clef = ClefValue.BASS

    private var cardPacket=CardPacket(emptyList())

    /**
     * Just enough to give the note some space between the clef and the edge
     */
    private var noteXOffset = LINE_SPACING

    private var noteYOffset = CENTER_STAFF

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return
        val staffYCenter = height / 2f
        val staffXCenter = width / 2f
        val xOffset = staffXCenter + noteXOffset

        //Draw staff
        staffArea.clef = clef
        staffArea.setDrawSpace(0f + padding, staffYCenter - LINE_SPACING * 2, width.toFloat() - padding)
        staffArea.draw(canvas)

        if (cardPacket.size <= 0 && cardPacket.size != CardPacket.FINISHED) {
            return
        }
        if (cardPacket.size == CardPacket.FINISHED){
            finishedList.postCall()
        }
        //Draw note
        noteArea.setDrawSpace(xOffset, staffYCenter + noteYOffset)
        noteArea.staffYCenter = staffYCenter
        noteArea.draw(canvas)
    }

    fun initializeCardPacket(listOfNotes: List<Note>, shuffle: Boolean = true, allowForSecondChance: Boolean = true,loop:Boolean = false) {
        cardPacket = CardPacket(listOfNotes,shuffle,allowForSecondChance,loop)
        currentNote = cardPacket.nextNote()
    }

    fun submitAnswer(answer: String): Boolean {
        return if (answer.toUpperCase(Locale.getDefault()) == currentNote.note.name.takeLast(1).toUpperCase(Locale.getDefault())) {
            currentNote = cardPacket.nextNote()
            Timber.d("Card was correct, moving to next card")
            true
        } else {
            if (cardPacket.allowForSecondChance) {
                cardPacket.addToRetry(currentNote)
                currentNote = cardPacket.nextNote()
            }
            false
        }
    }


    companion object {
        private val DEFAULT_NOTE = Note(ClefValue.TREBLE, NoteValue.MIDDLE_C)
    }

}