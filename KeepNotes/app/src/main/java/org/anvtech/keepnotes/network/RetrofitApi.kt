package org.anvtech.keepnotes.network


import org.anvtech.keepnotes.models.Notes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitApi {
    @GET("/get-notes")
    fun getNotes(): Call<List<Notes>>

    @POST("/create-note")
    fun createNote(@Body note: Notes)
}