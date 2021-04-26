package org.anvtech.keepnotes.network


import io.reactivex.rxjava3.core.Observable
import org.anvtech.keepnotes.models.Notes
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitApi {
    @GET("/get-notes")
    fun getNotes(): Observable<List<Notes>>

    @POST("/create-note")
    fun createNote(@Body note: Notes)
}