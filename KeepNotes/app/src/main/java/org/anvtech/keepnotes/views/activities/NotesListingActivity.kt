package org.anvtech.keepnotes.views.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.anvtech.keepnotes.Constants
import org.anvtech.keepnotes.R
import org.anvtech.keepnotes.viewmodels.NotesViewModel
import org.anvtech.keepnotes.views.NoteListAdapter

class NotesListingActivity : AppCompatActivity() {

    private lateinit var notesViewModel: NotesViewModel

    private lateinit var noteListAdapter: NoteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel::class.java)

        notesViewModel.getNotesListData().observe(this,
            { t ->
                layoutError.visibility = View.GONE
                if (t.isEmpty()) {
                    txtMessage.visibility = View.VISIBLE
                    txtMessage.text = getString(R.string.no_data)
                } else {
                    txtMessage.visibility = View.GONE
                    noteListAdapter.updateAdapter(t)
                }
            })

        notesViewModel.getErrorLiveData().observe(this, {
            layoutError.visibility = View.VISIBLE
            txtErrorMessage.text = getString(R.string.error_message)
            btnRetry.setOnClickListener {
                notesViewModel.fetchNotes()
            }
        })

        notesViewModel.getLoaderLiveData()
            .observe(this, { t ->
                if (t) {
                    progressBar.visibility = View.VISIBLE
                    layoutError.visibility = View.GONE
                } else
                    progressBar.visibility = View.GONE
            })

        notesViewModel.getNoteLiveData().observe(this,{
            noteListAdapter.updateAdapter(it)
            txtMessage.visibility = View.GONE
        })

        noteListAdapter = NoteListAdapter()

        notesViewModel.fetchNotes()

        listNotes.layoutManager = LinearLayoutManager(this)
        listNotes.adapter = noteListAdapter

        fab.setOnClickListener {
            val intent = Intent(this, CreateNoteActivity::class.java)
            startActivityForResult(intent, Constants.REQ_CODE_CREATE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.REQ_CODE_CREATE && resultCode == Activity.RESULT_OK) {
            val id=data?.getLongExtra(Constants.DATA,-1)
            notesViewModel.fetchNote(id)
        }
    }

}