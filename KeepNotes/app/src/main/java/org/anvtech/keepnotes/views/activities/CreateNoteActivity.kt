package org.anvtech.keepnotes.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_create_note.*
import org.anvtech.keepnotes.R
import org.anvtech.keepnotes.models.Notes
import org.anvtech.keepnotes.viewmodels.CreateNoteViewModel

class CreateNoteActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        val createNoteViewModel = ViewModelProviders.of(this).get(CreateNoteViewModel::class.java)

        createNoteViewModel.hasNoteSaved().observe(this, {
            if (it) {
                finish()
            } else {
                Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show()
            }
        })

        btnSave.setOnClickListener {
            if (editTitle.text.isNullOrBlank() || editDesc.text.isNullOrBlank()) {
                Toast.makeText(this, getString(R.string.error_note_empty), Toast.LENGTH_SHORT)
                    .show()
            } else {
                val notes =
                    Notes(title = editTitle.text.toString(), description = editDesc.text.toString())
                createNoteViewModel.createNote(notes)
            }
        }
    }
}