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
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Qualifier

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG: String = "NOTE_VIEW_MODEL"
    private val noteViewModel: NoteViewModel by viewModels()//nếu trên fragment thì sử dụng câu lệnh by activityViewModels()
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var doService: DoService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Log.d("HAU", doService.print())

        // Log.d(TAG, "MainActivity: ${noteViewModel.noteRepository} , $noteViewModel")
        // initControls()
        // initEvents()
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


/**
 * Dưới đây là cách để có thể inject mô interface vào project
 * ngoài ra qualifier sẽ giúp cho việc lấy nhiều đối tượng impl của interface
 *
 */

//b1 Tạo ra các qualifier để đánh giaau các impl khách nhau của interface MyService
//Dưới đây code làm cách nào để cung cấp các impl khác nhau của interface.
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl2

abstract
interface MyService {
    fun getAThing(): String
}

class DoService @Inject constructor(
    @Impl1 private val impl1: MyService,
    @Impl2 private val impl2: MyService
) {
    fun print() = "I got ${impl1.getAThing()}, and ${impl2.getAThing()}"
}

@Module
@InstallIn(SingletonComponent::class)
object MyServiceModule {
    @Provides
    @Impl1
    fun providerImpl1(): MyService = MyServiceImpl1()

    @Provides
    @Impl2
    fun providerImpl2(): MyService = MyServiceImpl2()

}

class MyServiceImpl1 @Inject constructor() : MyService {
    override fun getAThing(): String = "MyService 1"
}

class MyServiceImpl2 @Inject constructor() : MyService {
    override fun getAThing(): String = "MyService 2"
}