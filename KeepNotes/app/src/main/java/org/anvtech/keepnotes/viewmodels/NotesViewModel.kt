package org.anvtech.keepnotes.viewmodels

import androidx.lifecycle.ViewModel
import org.anvtech.keepnotes.network.RetrofitClient

class NotesViewModel : ViewModel() {
    private val retrofitApi = RetrofitClient.getApiInstance()


}