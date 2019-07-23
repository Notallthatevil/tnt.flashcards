package com.trippntechnology.tnt.flashcards.objects.noteconfiguration

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.trippntechnology.tnt.flashcards.objects.enums.clefvalue.ClefValue
import com.trippntechnology.tnt.flashcards.objects.enums.notevalue.NoteValue
import com.trippntechnology.tnt.flashcards.objects.note.Note

@Entity(tableName = "note_configuration")
data class NoteConfiguration(
    @PrimaryKey(autoGenerate = true) var id: Long = 0, val name: String, val notes: String
) {

    constructor(id: Long = 0, name: String, noteList: List<Note>) : this(
        id,
        name,
        notesToString(noteList)
    )

    override fun toString(): String {
        return "$name, id = $id"
    }

    companion object {
        fun notesToString(notes: List<Note>): String {
            var dataString = ""
            notes.forEachIndexed { index, note ->
                dataString += "${note.clef.name}:${note.note.name}"
                if (index < notes.size - 1) {
                    dataString += ", "
                }
            }
            return dataString
        }

        fun stringToNotes(dataString: String): List<Note> {
            val list = mutableListOf<Note>()
            val splitString = dataString.split(", ", ":")
            for (i in 0 until splitString.size step 2) {
                list.add(
                    Note(
                        ClefValue.valueOf(splitString[i]), NoteValue.valueOf(splitString[i + 1])
                    )
                )
            }
            return list
        }
    }
}