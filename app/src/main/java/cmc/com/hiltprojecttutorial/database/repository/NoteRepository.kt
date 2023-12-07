package cmc.com.hiltprojecttutorial.database.repository

import android.util.Log
import androidx.lifecycle.LiveData
import cmc.com.hiltprojecttutorial.database.dao.NoteDao
import cmc.com.hiltprojecttutorial.model.Note
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(private val noteDao: NoteDao) {
    suspend fun insertNote(note: Note) = noteDao.insertNote(note = note)
    suspend fun updateNote(note: Note) = noteDao.updateNode(node = note)
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note = note)
    fun getAllNote(): LiveData<List<Note>> = noteDao.getAllNote()
    //suspend fun getNoteByTitle(title: String) = noteDao.getNoteByTitle(title = title)
}