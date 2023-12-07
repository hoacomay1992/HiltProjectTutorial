package cmc.com.hiltprojecttutorial.ui.update

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import cmc.com.hiltprojecttutorial.R
import cmc.com.hiltprojecttutorial.databinding.ActivityUpdateNoteBinding
import cmc.com.hiltprojecttutorial.model.Note
import cmc.com.hiltprojecttutorial.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UpdateNoteActivity : AppCompatActivity() {
    private val TAG = "NOTE_VIEW_MODEL"
    private val noteViewModel: NoteViewModel by viewModels()
    private lateinit var binding: ActivityUpdateNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_note)
        Log.d(TAG, "UpdateNoteActivity: ${noteViewModel.noteRepository} , $noteViewModel")

        val note = intent.getSerializableExtra("UPDATE_NOTE") as Note
        binding.edtNoteTitle.setText(note.title)
        binding.edtNoteDes.setText(note.description)
        binding.btnUpdate.setOnClickListener {
            note.title = binding.edtNoteTitle.text.toString()
            note.description = binding.edtNoteDes.text.toString()
            noteViewModel.updateNote(note)
            finish()
        }
    }
}