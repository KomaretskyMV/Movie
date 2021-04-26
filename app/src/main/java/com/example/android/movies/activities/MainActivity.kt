package com.example.android.movies.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.android.movies.R
import com.example.android.movies.data.MovieAdapter
import com.example.android.movies.model.Movie
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var requestQueue : RequestQueue
    private val movies : MutableList<Movie> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.hasFixedSize()
        recyclerView.layoutManager = LinearLayoutManager(this)

        requestQueue = Volley.newRequestQueue(this)

        getMovies()

    }

    private fun getMovies() {
        val url = "http://www.omdbapi.com/?apikey=3b8a83ef&s=superman"
        val request = JsonObjectRequest(
            Request.Method.GET,
            url, null, { response ->
                try {
                    val jsonArray = response.getJSONArray("Search")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val title = jsonObject.getString("Title")
                        val year = jsonObject.getString("Year")
                        val posterUrl = jsonObject.getString("Poster")
                        val movie = Movie()
                        movie.title = title
                        movie.year = year
                        movie.posterUrl = posterUrl
                        movies.add(movie)
                    }
                    movieAdapter = MovieAdapter(this@MainActivity, movies)
                    recyclerView.adapter = movieAdapter
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }) { error -> error.printStackTrace() }
        requestQueue.add(request)
    }

}