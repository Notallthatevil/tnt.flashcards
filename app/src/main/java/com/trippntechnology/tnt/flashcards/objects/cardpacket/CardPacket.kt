package com.trippntechnology.tnt.flashcards.objects.cardpacket

import com.trippntechnology.tnt.flashcards.objects.enums.clefvalue.ClefValue
import com.trippntechnology.tnt.flashcards.objects.enums.notevalue.NoteValue
import com.trippntechnology.tnt.flashcards.objects.note.Note
import timber.log.Timber

class CardPacket(listOfNotes: List<Note>, shuffle: Boolean = true, val allowForSecondChance: Boolean = true, private val loop: Boolean = false) {

    /**
     * Initialize at negative one to immediately be able to call nextCard()
     */
    private var position = -1

    var size = listOfNotes.size
        private set(value) {
            field = value
        }

    private val cardPacket = listOfNotes.toMutableList()
    private val secondTryPacket = mutableListOf<Note>()

    private var retryPacketAdded = false

    init {
        if (shuffle) {
            cardPacket.shuffle()
        }
    }

    fun addToRetry(note: Note) {
        if (allowForSecondChance) {
            Timber.d("Adding card to second try pack")
            secondTryPacket.add(note)
        }
    }

    fun nextNote(): Note {
        return when {
            position + 1 < size -> {
                position++
                Timber.d("Moving on to next card")
                returnCurrentNote()
            }
            allowForSecondChance && secondTryPacket.size > 0 && !retryPacketAdded -> {
                position++
                Timber.d("End has been reached, adding second try pack")
                cardPacket.addAll(secondTryPacket)
                secondTryPacket.clear()
                retryPacketAdded = true
                returnCurrentNote()
            }
            loop -> {
                Timber.d("End has been reached, looping back to beginning")
                position = 0
                returnCurrentNote()
            }
            else -> {
                Timber.d("End has been reached")
                size = FINISHED
                return Note(ClefValue.TREBLE, NoteValue.MIDDLE_C)
            }
        }
    }

    fun prevNote(): Note {
        return when {
            position - 1 >= 0 -> {
                position--
                returnCurrentNote()
            }
            loop -> {
                position = size - 1
                returnCurrentNote()
            }
            else -> {
                size = FINISHED
                return Note(ClefValue.TREBLE, NoteValue.MIDDLE_C)
            }
        }
    }

    fun restart(): Note {
        position = 0
        return returnCurrentNote()
    }

    private fun returnCurrentNote(): Note {
        return cardPacket[position]
    }

    companion object {
        const val FINISHED = -10
    }
}