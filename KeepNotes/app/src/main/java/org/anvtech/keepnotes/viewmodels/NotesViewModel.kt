package org.anvtech.keepnotes.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.anvtech.keepnotes.models.Notes
import org.anvtech.keepnotes.network.RetrofitClient
import org.anvtech.keepnotes.repository.NotesRepository

class NotesViewModel : ViewModel() {

    private val notesRepository = NotesRepository()

    private var notesList = MutableLiveData<List<Notes>>()

    private var noteLiveData=MutableLiveData<Notes>()

    private var loaderLiveData = MutableLiveData<Boolean>()

    private var errorLiveData = MutableLiveData<Throwable>()

    fun getNotesListData(): LiveData<List<Notes>> {
        return notesList
    }

    fun getLoaderLiveData(): LiveData<Boolean> {
        return loaderLiveData
    }

    fun getErrorLiveData(): LiveData<Throwable> {
        return errorLiveData
    }

    fun getNoteLiveData():LiveData<Notes>{
        return noteLiveData
    }

    fun fetchNote(id:Long?){
        loaderLiveData.postValue(true)
        val note=notesRepository.getNote(id)
        note.subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.computation())
            .subscribe(object : Observer<Notes> {
                override fun onNext(t: Notes?) {
                    loaderLiveData.postValue(false)
                    noteLiveData.postValue(t)
                }

                override fun onError(e: Throwable?) {
                    loaderLiveData.postValue(false)
//                    errorLiveData.postValue(e)
                }

                override fun onComplete() {
                    loaderLiveData.postValue(false)
                }

                override fun onSubscribe(d: Disposable?) {
//                    TODO
                }

            })
    }

    fun fetchNotes() {
        loaderLiveData.postValue(true)
        val notesListing = notesRepository.getNotesListing()
        notesListing.subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.computation())
            .subscribe(object : Observer<List<Notes>> {
                override fun onNext(t: List<Notes>?) {
                    loaderLiveData.postValue(false)
                    notesList.postValue(t)
                }

                override fun onError(e: Throwable?) {
                    loaderLiveData.postValue(false)
                    errorLiveData.postValue(e)
                }

                override fun onComplete() {
                    loaderLiveData.postValue(false)
                }

                override fun onSubscribe(d: Disposable?) {
//                    TODO
                }

            })
    }

}