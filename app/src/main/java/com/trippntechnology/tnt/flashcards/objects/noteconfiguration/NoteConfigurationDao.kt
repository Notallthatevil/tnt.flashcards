package com.trippntechnology.tnt.flashcards.objects.noteconfiguration

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteConfigurationDao {
    @Insert
    fun insert(noteConfiguration: NoteConfiguration): Long

    @Update
    fun update(noteConfiguration: NoteConfiguration)

    @Query("DELETE FROM note_configuration")
    fun deleteAll()

    @Query("SELECT * FROM note_configuration")
    fun selectAll(): List<NoteConfiguration>

    @Query("SELECT * FROM note_configuration")
    fun selectAllLiveData(): LiveData<List<NoteConfiguration>>

    @Query("SELECT * FROM note_configuration WHERE id = :id")
    fun getById(id: Long):LiveData<NoteConfiguration>

    @Query("DELETE FROM note_configuration WHERE id = :id")
    fun deleteById(id: Long)

    @Query("SELECT count(1) FROM note_configuration")
    fun count(): Long
}