package org.anvtech.keepnotes.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_note_detail.*
import kotlinx.android.synthetic.main.layout_note_view.*
import kotlinx.android.synthetic.main.layout_note_view.txtDesc
import kotlinx.android.synthetic.main.layout_note_view.txtTitle
import org.anvtech.keepnotes.Constants
import org.anvtech.keepnotes.R
import org.anvtech.keepnotes.models.Notes

class NoteDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        val note: Notes = intent.extras?.getSerializable(Constants.DATA) as Notes

        txtTitle.text = note.title
        txtDesc.text = note.description
        txtCreated.text = note.createdOn
    }
}