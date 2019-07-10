package com.trippntechnology.tnt.flashcards.objects.enablednotes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EnabledNotesDao {
    @Insert
    fun insert(enabledNotes: EnabledNotes): Long

    @Update
    fun update(enabledNotes: EnabledNotes)

    @Query("DELETE FROM enabled_notes")
    fun deleteAll()

    @Query("SELECT * FROM enabled_notes")
    fun selectAll(): List<EnabledNotes>

    @Query("SELECT * FROM enabled_notes")
    fun selectAllLiveData(): LiveData<List<EnabledNotes>>

    @Query("SELECT * FROM enabled_notes WHERE id = :id")
    fun getById(id: Long):EnabledNotes

    @Query("DELETE FROM enabled_notes WHERE id = :id")
    fun deleteById(id: Long)

    @Query("SELECT count(1) FROM enabled_notes")
    fun count(): Long
}