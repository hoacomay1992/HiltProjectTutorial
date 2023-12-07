package cmc.com.hiltprojecttutorial.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import cmc.com.hiltprojecttutorial.R
import cmc.com.hiltprojecttutorial.databinding.ActivityAddNoteBinding
import cmc.com.hiltprojecttutorial.model.Note
import cmc.com.hiltprojecttutorial.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddNoteActivity : AppCompatActivity() {
    private val TAG = "NOTE_VIEW_MODEL"

    private val noteViewModel: NoteViewModel by viewModels()
    private lateinit var binding: ActivityAddNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note)
        Log.d(TAG, "AddNoteActivity: ${noteViewModel.noteRepository} , $noteViewModel")
        binding.btnAdd.setOnClickListener {
            val note = Note(binding.edtNoteTitle.text.toString(), binding.edtNoteDes.text.toString())
            noteViewModel.insertNote(note)
            finish()
        }
    }
}