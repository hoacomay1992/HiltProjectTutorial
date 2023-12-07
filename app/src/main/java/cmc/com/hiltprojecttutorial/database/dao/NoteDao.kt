package cmc.com.hiltprojecttutorial.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cmc.com.hiltprojecttutorial.model.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNode(node: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("select * from note_table")
    fun getAllNote(): LiveData<List<Note>>

//    @Query("SELECT * FROM note_table WHERE title_col =:title")
//    suspend fun getNoteByTitle(title: String): LiveData<Note>
}