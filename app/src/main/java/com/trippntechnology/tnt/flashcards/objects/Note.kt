package com.trippntechnology.tnt.flashcards.objects

data class Note(val clef: ClefValue, val note: NoteValue) {

    companion object {
        val trebleNotes = List(17) {
            Note(ClefValue.TREBLE, NoteValue.MIDDLE_A)
            Note(ClefValue.TREBLE, NoteValue.MIDDLE_B)
            Note(ClefValue.TREBLE, NoteValue.MIDDLE_C)
            Note(ClefValue.TREBLE, NoteValue.MIDDLE_D)
            Note(ClefValue.TREBLE, NoteValue.MIDDLE_E)
            Note(ClefValue.TREBLE, NoteValue.MIDDLE_F)
            Note(ClefValue.TREBLE, NoteValue.MIDDLE_G)
            Note(ClefValue.TREBLE, NoteValue.HIGH_A)
            Note(ClefValue.TREBLE, NoteValue.HIGH_B)
            Note(ClefValue.TREBLE, NoteValue.HIGH_C)
            Note(ClefValue.TREBLE, NoteValue.HIGH_D)
            Note(ClefValue.TREBLE, NoteValue.HIGH_E)
            Note(ClefValue.TREBLE, NoteValue.HIGH_F)
            Note(ClefValue.TREBLE, NoteValue.HIGH_G)
            Note(ClefValue.TREBLE, NoteValue.X_HIGH_A)
            Note(ClefValue.TREBLE, NoteValue.X_HIGH_B)
            Note(ClefValue.TREBLE, NoteValue.X_HIGH_C)

        }
        val bassNotes = List(17) {
            Note(ClefValue.BASS, NoteValue.X_LOW_C)
            Note(ClefValue.BASS, NoteValue.X_LOW_D)
            Note(ClefValue.BASS, NoteValue.X_LOW_E)
            Note(ClefValue.BASS, NoteValue.X_LOW_F)
            Note(ClefValue.BASS, NoteValue.X_LOW_G)
            Note(ClefValue.BASS, NoteValue.LOW_A)
            Note(ClefValue.BASS, NoteValue.LOW_B)
            Note(ClefValue.BASS, NoteValue.LOW_C)
            Note(ClefValue.BASS, NoteValue.LOW_D)
            Note(ClefValue.BASS, NoteValue.LOW_E)
            Note(ClefValue.BASS, NoteValue.LOW_F)
            Note(ClefValue.BASS, NoteValue.LOW_G)
            Note(ClefValue.BASS, NoteValue.MIDDLE_A)
            Note(ClefValue.BASS, NoteValue.MIDDLE_B)
            Note(ClefValue.BASS, NoteValue.MIDDLE_C)
            Note(ClefValue.BASS, NoteValue.MIDDLE_D)
            Note(ClefValue.BASS, NoteValue.MIDDLE_E)

        }


        fun getNoteTranslation(note: Note, lineSpacing: Float): Float {
            val fullStepDown = lineSpacing
            val fullStepUp = -lineSpacing
            val halfStepDown = fullStepDown / 2
            val halfStepUp = fullStepUp / 2
            when (note.clef) {
                ClefValue.TREBLE -> return when (note.note) {
                    NoteValue.X_LOW_C -> fullStepDown * 10
                    NoteValue.X_LOW_D -> fullStepDown * 9 + halfStepDown
                    NoteValue.X_LOW_E -> fullStepDown * 9
                    NoteValue.X_LOW_F -> fullStepDown * 8 + halfStepDown
                    NoteValue.X_LOW_G -> fullStepDown * 8
                    NoteValue.LOW_A -> fullStepDown * 7 + halfStepDown
                    NoteValue.LOW_B -> fullStepDown * 7
                    NoteValue.LOW_C -> fullStepDown * 6 + halfStepDown
                    NoteValue.LOW_D -> fullStepDown * 6
                    NoteValue.LOW_E -> fullStepDown * 5 + halfStepDown
                    NoteValue.LOW_F -> fullStepDown * 5
                    NoteValue.LOW_G -> fullStepDown * 4 + halfStepDown
                    NoteValue.MIDDLE_A -> fullStepDown * 4
                    NoteValue.MIDDLE_B -> fullStepDown * 3 + halfStepDown
                    NoteValue.MIDDLE_C -> fullStepDown * 3
                    NoteValue.MIDDLE_D -> fullStepDown * 2 + halfStepDown
                    NoteValue.MIDDLE_E -> fullStepDown * 2
                    NoteValue.MIDDLE_F -> fullStepDown + halfStepDown
                    NoteValue.MIDDLE_G -> fullStepDown
                    NoteValue.HIGH_A -> halfStepDown
                    NoteValue.HIGH_B -> 0f
                    NoteValue.HIGH_C -> halfStepUp
                    NoteValue.HIGH_D -> fullStepUp
                    NoteValue.HIGH_E -> fullStepUp + halfStepUp
                    NoteValue.HIGH_F -> fullStepUp * 2
                    NoteValue.HIGH_G -> fullStepUp * 2 + halfStepUp
                    NoteValue.X_HIGH_A -> fullStepUp * 3
                    NoteValue.X_HIGH_B -> fullStepUp * 3 + halfStepUp
                    NoteValue.X_HIGH_C -> fullStepUp * 4
                }
                ClefValue.BASS -> return when (note.note) {
                    NoteValue.X_LOW_C -> fullStepDown * 4
                    NoteValue.X_LOW_D -> fullStepDown * 3 + halfStepDown
                    NoteValue.X_LOW_E -> fullStepDown * 3
                    NoteValue.X_LOW_F -> fullStepDown * 2 + halfStepDown
                    NoteValue.X_LOW_G -> fullStepDown * 2
                    NoteValue.LOW_A -> fullStepDown + halfStepDown
                    NoteValue.LOW_B -> fullStepDown
                    NoteValue.LOW_C -> halfStepDown
                    NoteValue.LOW_D -> 0f
                    NoteValue.LOW_E -> halfStepUp
                    NoteValue.LOW_F -> fullStepUp
                    NoteValue.LOW_G -> fullStepUp + halfStepUp
                    NoteValue.MIDDLE_A -> fullStepUp * 2
                    NoteValue.MIDDLE_B -> fullStepUp * 2 + halfStepUp
                    NoteValue.MIDDLE_C -> fullStepUp * 3
                    NoteValue.MIDDLE_D -> fullStepUp * 3 + halfStepUp
                    NoteValue.MIDDLE_E -> fullStepUp * 4
                    NoteValue.MIDDLE_F -> fullStepUp * 4 + halfStepUp
                    NoteValue.MIDDLE_G -> fullStepUp * 5
                    NoteValue.HIGH_A -> fullStepUp * 5 + halfStepUp
                    NoteValue.HIGH_B -> fullStepUp * 6
                    NoteValue.HIGH_C -> fullStepUp * 6 + halfStepUp
                    NoteValue.HIGH_D -> fullStepUp * 7
                    NoteValue.HIGH_E -> fullStepUp * 7 + halfStepUp
                    NoteValue.HIGH_F -> fullStepUp * 8
                    NoteValue.HIGH_G -> fullStepUp * 8 + halfStepUp
                    NoteValue.X_HIGH_A -> fullStepUp * 9
                    NoteValue.X_HIGH_B -> fullStepUp * 9 + halfStepUp
                    NoteValue.X_HIGH_C -> fullStepUp * 10
                }
            }
        }
    }
}