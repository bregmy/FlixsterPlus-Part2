package com.example.flixsterplus

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

private const val TAG="DetailActivity"
class DetailActivity : AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var headshots: ImageView
    private lateinit var Desc: TextView
    private lateinit var poster: ImageView
    private lateinit var knownfor: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        title=findViewById(R.id.titles)
        headshots=findViewById(R.id.headshot)
        Desc=findViewById(R.id.tvOverview)
        poster=findViewById(R.id.Poster)
        knownfor=findViewById(R.id.knownfor)



        val movie=intent.getParcelableExtra<Movie>(MOVIE_EXTRA) as Movie
        Log.i(TAG, "Movie is $movie")
        title.text=movie.name

        Glide.with(this)
            .load(movie.ImageUrl)
            .into(poster)

        Glide.with(this)
            .load(movie.posterImageUrl)
            .into(headshots)
        Desc.text=movie.description
        knownfor.text=movie.title


    }
}