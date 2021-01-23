package com.example.mountyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mountyapp.adapters.AdapterTopRatedMovies
import com.example.mountyapp.adapters.AdapterUpcomingMovies
import com.example.mountyapp.dto.TopRatedMovie
import com.example.mountyapp.dto.UpcomingMovie
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(),KodeinAware {
    override val kodein by kodein()
    private val factory: MainViewModelFactory by instance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewmodel: MainViewModel by lazy {
            ViewModelProvider(this@MainActivity, factory).get(MainViewModel::class.java) }
        var movieList = Observer<TopRatedMovie> { movielist ->
            if (movielist.results?.size!! > 0) {
                var id = ArrayList<String>()
                var name = ArrayList<String>()
                var img = ArrayList<String>()
                for (i in movielist.results) {
                    id.add(i?.id.toString())
                    name.add(i?.originalTitle.toString())
                    img.add(i?.posterPath.toString())
                }
                val manager: RecyclerView.LayoutManager =
                    LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
                recyclerView?.layoutManager = manager
                recyclerView?.adapter = AdapterTopRatedMovies(id, name, img, applicationContext)
                (recyclerView.adapter as AdapterTopRatedMovies).notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "No Record Found", Toast.LENGTH_SHORT).show()
            }
        }


        var upcomingmovie = Observer<UpcomingMovie> { upcomingmovie ->
            if (upcomingmovie.results?.size!! > 0) {
                var id = ArrayList<String>()
                var name = ArrayList<String>()
                var img = ArrayList<String>()
                for (i in upcomingmovie.results) {
                    id.add(i?.id.toString())
                    name.add(i?.originalTitle.toString())
                    img.add(i?.posterPath.toString())
                }
                val manager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
                rv2?.layoutManager = manager
                rv2?.adapter = AdapterUpcomingMovies(id, name, img, applicationContext)
                (rv2.adapter as AdapterUpcomingMovies).notifyDataSetChanged()
            } else {
                Toast.makeText(applicationContext, "No Record Found", Toast.LENGTH_SHORT).show()
            }
        }
        viewmodel.getmyTrendData()?.observe(this, upcomingmovie)
        viewmodel.getmyData()?.observe(this, movieList)



    }
}