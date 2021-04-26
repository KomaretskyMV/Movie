package com.example.android.movies.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movies.R
import com.example.android.movies.model.Movie
import com.squareup.picasso.Picasso

class MovieAdapter(private val context: Context,private val movies : MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val posterImageView = itemView.findViewById<ImageView>(R.id.posterImageView)
            val titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
            val yearTextView = itemView.findViewById<TextView>(R.id.yearTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = movies[position]
        val title = currentMovie.title
        val year = currentMovie.year
        val posterUrl = currentMovie.posterUrl

        holder.titleTextView.text = title
        holder.yearTextView.text = year
        Picasso.get().load(posterUrl).fit().centerInside().into(holder.posterImageView)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}