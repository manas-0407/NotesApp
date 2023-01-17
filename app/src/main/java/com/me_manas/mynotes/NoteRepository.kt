package com.me_manas.mynotes

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteSDAO) {
    val allnotes: LiveData<List<Note>> = noteDao.getAlphabetizedNote()

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }
}