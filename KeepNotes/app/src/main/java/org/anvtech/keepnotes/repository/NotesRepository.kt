package org.anvtech.keepnotes.repository

import io.reactivex.rxjava3.core.Observable
import org.anvtech.keepnotes.models.Notes
import org.anvtech.keepnotes.network.RetrofitClient

class NotesRepository {
    private val retrofitApi = RetrofitClient.getApiInstance()

    fun getNotesListing(): Observable<List<Notes>> {
        return retrofitApi.getNotes();
    }

    fun getNote(id: Long?): Observable<Notes> {
        return retrofitApi.getNotes(id)
    }

    fun createNote(note: Notes): Observable<Long> {
        return retrofitApi.createNote(note)
    }

    fun updateNote(note: Notes): Observable<Long> {
        return retrofitApi.updateNote(note)
    }
}