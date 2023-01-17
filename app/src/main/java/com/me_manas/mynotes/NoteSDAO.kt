package com.me_manas.mynotes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteSDAO {

    @Query("SELECT * FROM Notes_Table ORDER BY id ASC")
    fun getAlphabetizedNote(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)
}