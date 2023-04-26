package com.example.flixsterplus

import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import org.w3c.dom.Text

const val MOVIE_EXTRA="MOVIE_EXTRA"
private const val TAG="MovieAdapter"

class MovieAdapter(private val context: Context, private val movies: List<Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
           val view=  LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie= movies[position]
        holder.bind(movie)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        private val ivPoster= itemView.findViewById<ImageView>(R.id.imageView)

        private val tvTitle= itemView.findViewById<TextView>(R.id.names)


        //private val tvOverview=itemView.findViewById<TextView>(R.id.tvOverview)

        init{
            itemView.setOnClickListener(this)
        }
        fun bind(movie:Movie){
            tvTitle.text=movie.name

            //tvOverview.text=movie.overview
            Glide.with(context).load(movie.posterImageUrl).into(ivPoster)




        }

        override fun onClick(v: View?) {
            val movie=movies[adapterPosition]
            Toast.makeText(context, movie.name, Toast.LENGTH_SHORT).show()
            val intent= Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)
        }
    }

}
