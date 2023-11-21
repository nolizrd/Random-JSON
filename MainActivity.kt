package com.example.audit14

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private lateinit var movies: MutableList<Movie>
    private lateinit var randomButton: Button
    private lateinit var resetButton: Button
    private lateinit var name: TextView
    private lateinit var year: TextView
    private lateinit var rating: TextView
    private lateinit var dir: TextView
    private lateinit var act: TextView
    private lateinit var runt: TextView
    private lateinit var moviesData: Movies
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        randomButton = findViewById(R.id.randomButton)
        name = findViewById(R.id.name)
        resetButton = findViewById(R.id.resetButton)
        year = findViewById(R.id.year)
        rating = findViewById(R.id.rating)
        act = findViewById(R.id.act)
        dir = findViewById(R.id.dir)
        runt = findViewById(R.id.runt)

        loadMoviesData()

        randomButton.setOnClickListener {
            onStart()
        }

        resetButton.setOnClickListener {
            onClearClick()
        }
    }

    private fun loadMoviesData() {
        val moviesStream = resources.openRawResource(R.raw.movies)
        val gson = Gson()
        moviesData = gson.fromJson(InputStreamReader(moviesStream), Movies::class.java)
        movies = moviesData.movies.toMutableList()
    }

    override fun onStart() {
        super.onStart()
        if (movies.isEmpty()) {
            name.text = "Все фильмы просмотрены"
            year.text = ""
            rating.text = ""
            dir.text = ""
            act.text = ""
            runt.text = ""
            return
        }

        val randomIndex = (0 until movies.size).random()
        val randomMovie = movies.removeAt(randomIndex)

        name.text = randomMovie.name
        year.text = randomMovie.year.toString()
        rating.text = randomMovie.rating.toString()
        dir.text = randomMovie.director
        act.text = randomMovie.actors
        runt.text = randomMovie.time
        currentIndex += 1
    }

    fun onClearClick() {
        name.text = "Список фильмов сброшен"
        year.text = ""
        rating.text = ""
        dir.text = ""
        act.text = ""
        runt.text = ""

        currentIndex = 0
        loadMoviesData() // Повторное чтение файла при сбросе списка фильмов
    }
}
