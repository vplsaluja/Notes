package org.anvtech.keepnotes.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.anvtech.keepnotes.R
import org.anvtech.keepnotes.models.Notes

class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder>() {

    private var listNotes = ArrayList<Notes>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_note_view, parent, false)
        return NoteListViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        val notes = listNotes[position]
        holder.txtTitle?.text = notes.title
        holder.txtDesc?.text = notes.description
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    fun updateAdapter(listNotes: List<Notes>?) {
        if (listNotes != null) {
            this.listNotes.addAll(listNotes)
        }
        notifyDataSetChanged()
    }

    fun updateAdapter(note: Notes) {
        var indexOf = -1
        for (tempNote in listNotes) {
            if (tempNote.id == note.id) {
                indexOf = listNotes.indexOf(tempNote)
                break
            }
        }
        if (indexOf != -1) {
            listNotes.removeAt(indexOf)
            listNotes.add(indexOf, note)
        } else {
            listNotes.add(0, note)
            indexOf = 0
        }
        notifyItemChanged(indexOf)
    }

    class NoteListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView? = itemView.findViewById(R.id.txtTitle)
        val txtDesc: TextView? = itemView.findViewById(R.id.txtDesc)
    }
}