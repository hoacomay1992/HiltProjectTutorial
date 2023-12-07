package cmc.com.hiltprojecttutorial.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import cmc.com.hiltprojecttutorial.R
import cmc.com.hiltprojecttutorial.adapter.NoteAdapter
import cmc.com.hiltprojecttutorial.databinding.ActivityMainBinding
import cmc.com.hiltprojecttutorial.model.Note
import cmc.com.hiltprojecttutorial.ui.add.AddNoteActivity
import cmc.com.hiltprojecttutorial.ui.update.UpdateNoteActivity
import cmc.com.hiltprojecttutorial.viewmodel.NoteViewModel
import cmc.com.hiltprojecttutorial.viewmodel.NoteViewModel_Factory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG: String = "NOTE_VIEW_MODEL"
    private val noteViewModel: NoteViewModel by viewModels()//nếu trên fragment thì sử dụng câu lệnh by activityViewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Log.d(TAG, "MainActivity: ${noteViewModel.noteRepository} , $noteViewModel")
        initControls()
        initEvents()
    }

    private fun initEvents() {
        binding.btnOpenAddActivity.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initControls() {
        val adapter = NoteAdapter(this@MainActivity, onItemClick, onItemDelete)
        binding.rvNote.setHasFixedSize(true)
        binding.rvNote.layoutManager = LinearLayoutManager(this)
        binding.rvNote.adapter = adapter
        noteViewModel.getAllNote().observe(this) {
            adapter.setNote(it)
        }

    }

    private val onItemClick: (Note) -> Unit = {
        val intent = Intent(this, UpdateNoteActivity::class.java)
        intent.putExtra("UPDATE_NOTE", it)
        startActivity(intent)

    }
    private val onItemDelete: (Note) -> Unit = {
        noteViewModel.deleteNote(it)
    }
}