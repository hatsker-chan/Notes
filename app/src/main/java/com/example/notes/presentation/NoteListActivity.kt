package com.example.notes.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.MyApplication
import com.example.notes.R
import com.example.notes.databinding.ActivityNoteListBinding
import com.example.notes.presentation.adapter.NoteListAdapter
import javax.inject.Inject

class NoteListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as MyApplication).component
    }

    private val binding by lazy {
        ActivityNoteListBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NoteListViewModel::class.java]
    }

    private val noteListAdapter by lazy {
        NoteListAdapter()
    }

    private val settings by lazy {
        PreferenceManager.getDefaultSharedPreferences(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component.inject(this)

        setTheme(settings.getInt(EXTRA_THEME_KEY, -1))
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setupRv()

        viewModel.notes.observe(this) {
            noteListAdapter.submitList(it)
        }

        binding.btAddItem.setOnClickListener {
            val intent = NoteItemActivity.newIntentAddMode(this)
            startActivity(intent)
        }

        supportActionBar?.title = getString(R.string.main_screen_action_bar)
    }


    private fun setSwipeListener() {
        val callback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val note = noteListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.removeNote(note.id)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)

        itemTouchHelper.attachToRecyclerView(binding.rvNoteList)
    }

    private fun setupRv() {
        binding.rvNoteList.adapter = noteListAdapter

        noteListAdapter.onNoteClickListener = {
            val intent = NoteItemActivity.newIntentEditMode(this, it)
            startActivity(intent)
        }

        noteListAdapter.onNoteLongClickListener = {
            viewModel.addNoteToFavourites(it)
        }
        setSwipeListener()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_favourites -> {
                val intent = NoteListOfFavouritesActivity.newIntent(this)
                startActivity(intent)
            }

            R.id.item_settings -> {
                val intent = SettingsActivity.newIntent(this)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, NoteListActivity::class.java)
        }

        const val EXTRA_THEME_KEY = "theme"
    }
}