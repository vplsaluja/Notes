package org.anvtech.keepnotes.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.anvtech.keepnotes.R
import org.anvtech.keepnotes.models.Notes
import org.anvtech.keepnotes.viewmodels.NotesViewModel

class NotesListingActivity : AppCompatActivity() {

    private lateinit var notesViewModel: NotesViewModel

    private lateinit var noteListAdapter: NoteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)

        notesViewModel.getNotesListData().observe(this,
            { t -> noteListAdapter.updateAdapter(t) })

        notesViewModel.getLoaderLiveData()
            .observe(this, { t -> progressBar.visibility = if (t) View.VISIBLE else View.GONE })

        notesViewModel.fetchNotes()

        noteListAdapter = NoteListAdapter()

        listNotes.layoutManager = LinearLayoutManager(this)
        listNotes.adapter = noteListAdapter

    }
}