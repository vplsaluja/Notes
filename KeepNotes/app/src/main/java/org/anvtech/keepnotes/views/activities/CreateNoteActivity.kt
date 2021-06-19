package org.anvtech.keepnotes.views.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_create_note.*
import org.anvtech.keepnotes.Constants
import org.anvtech.keepnotes.R
import org.anvtech.keepnotes.models.Notes
import org.anvtech.keepnotes.viewmodels.CreateNoteViewModel

class CreateNoteActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        var note: Notes? = null
        if (intent.getSerializableExtra(Constants.DATA) != null)
            note = intent.getSerializableExtra(Constants.DATA) as Notes

        editTitle.setText(note?.title)
        editDesc.setText(note?.description)

        val createNoteViewModel = ViewModelProviders.of(this).get(CreateNoteViewModel::class.java)

        createNoteViewModel.hasNoteSaved().observe(this, {
            if (!it.equals(-1)) {
                val intent = Intent()
                val bundle = Bundle()
                bundle.putLong(Constants.DATA, it)
                intent.putExtras(bundle)
                setResult(Activity.RESULT_OK, intent)
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

                if (note == null) {
                    val notes =
                        Notes(
                            title = editTitle.text.toString(),
                            description = editDesc.text.toString()
                        )
                    createNoteViewModel.createNote(notes)
                } else {
                    val notes =
                        Notes(
                            id = note.id,
                            title = editTitle.text.toString(),
                            description = editDesc.text.toString()
                        )
                    createNoteViewModel.updateNote(notes)
                }
            }
        }
    }
}