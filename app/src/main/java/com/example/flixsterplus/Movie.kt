package com.example.flixsterplus

import android.os.Parcelable
import android.util.Log
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.IgnoredOnParcel
import org.json.JSONArray
import org.json.JSONObject

@Parcelize
data class Movie(
    val profile_path: String,
    val name: String,
    val description: String,
    val poster_path: String,
    val title: String

) : Parcelable {

    @IgnoredOnParcel
    val posterImageUrl = "https://image.tmdb.org/t/p/w500/$profile_path"
    val ImageUrl="https://image.tmdb.org/t/p/w500/$poster_path"
    companion object {
        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            for (i in 0 until movieJsonArray.length()) {
                val movieJson = movieJsonArray.getJSONObject(i)


                val knownForArray = movieJson.getJSONArray("known_for")

                var title = ""
                for (j in 0 until knownForArray.length()) {
                    val knownForObject = knownForArray.getJSONObject(j)
                    if (knownForObject.has("original_title")) {
                        title = knownForObject.getString("original_title")
                        break
                    }
                }
                if (title.isEmpty()) {
                    title = "Unknown"
                }

                val firstKnownForObject = knownForArray.getJSONObject(0)
                val overview = knownForArray.getJSONObject(0).getString("overview")
                val posterPath = knownForArray.getJSONObject(0).getString("poster_path")

                Log.d("MovieDebug", "firstKnownForObject: $firstKnownForObject")



                movies.add(
                    Movie(
                        movieJson.getString("profile_path"),
                        movieJson.getString("name"),
                        overview,
                        posterPath,
                        title

                    )
                )
            }
            return movies
        }

    }

}