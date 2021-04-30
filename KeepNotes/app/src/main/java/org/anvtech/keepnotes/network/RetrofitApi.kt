package org.anvtech.keepnotes.network


import io.reactivex.rxjava3.core.Observable
import org.anvtech.keepnotes.models.Notes
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitApi {
    @GET("/get-notes")
    fun getNotes(): Observable<List<Notes>>

    @GET("/get-notes/{id}")
    fun getNotes(@Path(value = "id", encoded = true) id: Long?): Observable<Notes>

    @POST("/create-note")
    fun createNote(@Body note: Notes): Observable<Long>
}