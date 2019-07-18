package com.trippntechnology.tnt.flashcards

import com.trippntechnology.tnt.flashcards.objects.noteconfiguration.NoteConfiguration
import com.trippntechnology.tnt.flashcards.objects.enums.clefvalue.ClefValue
import com.trippntechnology.tnt.flashcards.objects.enums.notevalue.NoteValue
import com.trippntechnology.tnt.flashcards.objects.note.Note
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun EnabledNotes_NotesToString() {
        val sut = NoteConfiguration.notesToString(
            listOf(
                Note(ClefValue.BASS, NoteValue.HIGH_A),
                Note(ClefValue.BASS, NoteValue.HIGH_B),
                Note(ClefValue.BASS, NoteValue.HIGH_C),
                Note(ClefValue.BASS, NoteValue.HIGH_D),
                Note(ClefValue.BASS, NoteValue.HIGH_E),
                Note(ClefValue.BASS, NoteValue.HIGH_F),
                Note(ClefValue.BASS, NoteValue.HIGH_G),
                Note(ClefValue.TREBLE, NoteValue.X_HIGH_A),
                Note(ClefValue.TREBLE, NoteValue.X_HIGH_B),
                Note(ClefValue.TREBLE, NoteValue.X_HIGH_C)
            )
        )
        val expected =
            "BASS:HIGH_A, BASS:HIGH_B, BASS:HIGH_C, BASS:HIGH_D, BASS:HIGH_E, BASS:HIGH_F, BASS:HIGH_G, TREBLE:X_HIGH_A, TREBLE:X_HIGH_B, TREBLE:X_HIGH_C"
        assertEquals(expected, sut)
    }

    @Test
    fun EnabledNotes_StringToNotes() {
        val sut =
            NoteConfiguration.stringToNotes("BASS:HIGH_A, BASS:HIGH_B, BASS:HIGH_C, BASS:HIGH_D, BASS:HIGH_E, BASS:HIGH_F, BASS:HIGH_G, TREBLE:X_HIGH_A, TREBLE:X_HIGH_B, TREBLE:X_HIGH_C")
        val expected =
            listOf(
                Note(ClefValue.BASS, NoteValue.HIGH_A),
                Note(ClefValue.BASS, NoteValue.HIGH_B),
                Note(ClefValue.BASS, NoteValue.HIGH_C),
                Note(ClefValue.BASS, NoteValue.HIGH_D),
                Note(ClefValue.BASS, NoteValue.HIGH_E),
                Note(ClefValue.BASS, NoteValue.HIGH_F),
                Note(ClefValue.BASS, NoteValue.HIGH_G),
                Note(ClefValue.TREBLE, NoteValue.X_HIGH_A),
                Note(ClefValue.TREBLE, NoteValue.X_HIGH_B),
                Note(ClefValue.TREBLE, NoteValue.X_HIGH_C)
            )
        assertEquals(expected, sut)
    }
}
