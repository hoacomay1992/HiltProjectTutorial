package cmc.com.hiltprojecttutorial.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cmc.com.hiltprojecttutorial.database.repository.NoteRepository
import cmc.com.hiltprojecttutorial.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(val noteRepository: NoteRepository) : ViewModel() {
    fun insertNote(note: Note) = viewModelScope.launch {
        noteRepository.insertNote(note = note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.updateNote(note = note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.deleteNote(note = note)
    }

    fun getAllNote(): LiveData<List<Note>> {
        return noteRepository.getAllNote()
    }
}