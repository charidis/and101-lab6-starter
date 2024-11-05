package com.driuft.random_pets_starter

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var userList = arrayListOf<User>()
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        fetchData()
    }
    private fun fetchData() {
        val reqQueue: RequestQueue = Volley.newRequestQueue(this)
        val apiSample = "https://api.nasa.gov/planetary/apod?count=10&api_key=xz0DZkL3FMoxVIjIubabrk2qqsD4sn0KgNGqi7ZN"
        val request = JsonArrayRequest(Request.Method.GET, apiSample, null,
            { response ->
                try {
                    for (i in 0 until response.length()) {
                        val item = response.getJSONObject(i)
                        val user = User(
                            item.getString("date"),
                            item.getString("explanation"),
                            item.getString("title"),
                            item.getString("url")
                        )
                        userList.add(user)
                    }

                    recyclerView?.layoutManager = LinearLayoutManager(this)
                    recyclerView?.adapter = UserAdapter(userList)
                } catch (e: JSONException) {
                    Log.e("API Call", "Error parsing JSON: ${e.message}")
                }
            },
            { error ->
                Log.e("API Call", "Error: $error")
            }
        )

        reqQueue.add(request)
    }}

