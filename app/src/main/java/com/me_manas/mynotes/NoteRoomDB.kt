package com.me_manas.mynotes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteRoomDB: RoomDatabase() {

    abstract fun noteDao(): NoteSDAO

    companion object{
        @Volatile
        private var INSTANCE: NoteRoomDB? = null

        fun getDatabase(context: Context): NoteRoomDB{

            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,NoteRoomDB::class.java,
                    "note_database")
                    .build()
                INSTANCE=instance
                return instance
            }
        }
    }
}