package com.me_manas.mynotes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes_Table")
class Note(val text: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}