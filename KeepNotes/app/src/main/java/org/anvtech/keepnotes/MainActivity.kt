package org.anvtech.keepnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<List<Notes>> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofitApi = RetrofitClient().initRetrofitClient()
        val observable = retrofitApi.fetchNotes();

        observable.enqueue(this)
    }

    override fun onResponse(call: Call<List<Notes>>, response: Response<List<Notes>>) {
        Log.e("Vipul","response={$response.body()}")
    }

    override fun onFailure(call: Call<List<Notes>>, t: Throwable) {
        Log.e("Vipul","error={${t.toString()}}")
    }

}