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

    class NoteListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView? = itemView.findViewById(R.id.txtTitle)
        val txtDesc: TextView? = itemView.findViewById(R.id.txtDesc)
    }
}