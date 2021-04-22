package org.anvtech.keepnotes


import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {
    @GET("/get-notes")
    fun fetchNotes():Call<List<Notes>>;
}