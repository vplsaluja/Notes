package org.anvtech.keepnotes.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.anvtech.keepnotes.models.Notes
import org.anvtech.keepnotes.repository.NotesRepository

class CreateNoteViewModel : ViewModel() {

    private var dataSaved = MutableLiveData<Boolean>()
    private val noteRepository = NotesRepository()

    fun hasNoteSaved(): LiveData<Boolean> {
        return dataSaved
    }

    fun createNote(note: Notes) {
        noteRepository.createNote(note)
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.computation())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(t: Int?) {
                    dataSaved.postValue(true)
                }

                override fun onError(e: Throwable?) {
                    e?.printStackTrace()
                    Log.e(CreateNoteViewModel::class.simpleName,"error="+e.toString())
                    dataSaved.postValue(false)
                }

                override fun onComplete() {
                }

            })
    }
}