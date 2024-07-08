package com.example.notesapp.data


import androidx.room.Database
import androidx.room.RoomDatabase

/*
-> By defining the class as abstract, you indicate that the actual implementation will be provided by Room,
   not manually by you.
-> This allows Room to handle the underlying database interactions, letting you focus on defining the data
   models, DAOs (Data Access Objects), and relationships.
 */

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){  // ye class room database ko extend kregi or hum room database k contructor ko call krenge

    // we will define Dao as an abstract object here
    abstract val dao : NoteDao
}