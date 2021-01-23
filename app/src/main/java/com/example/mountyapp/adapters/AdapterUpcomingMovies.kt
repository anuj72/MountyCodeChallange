package com.example.mountyapp.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mountyapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_movie_upcoming.view.*


class AdapterUpcomingMovies(val id: ArrayList<String>,
                             val name: ArrayList<String>,val img: ArrayList<String>
                             , val context: Context?): RecyclerView.Adapter<ViewHolderUpcoming>() {
          override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderUpcoming {
             return ViewHolderUpcoming(
            LayoutInflater.from(context).inflate(
                R.layout.adapter_movie_upcoming,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return id.size
       }

    override fun onBindViewHolder(holder: ViewHolderUpcoming, position: Int) {
        Picasso.get()
            .load("https://image.tmdb.org/t/p/w500/"+img[position])
            .placeholder(R.drawable.def)
            .error(R.drawable.def)
            .into(holder.img)
        holder.tv3.text=(name[position])
    } }

class ViewHolderUpcoming(view: View) : RecyclerView.ViewHolder(view) {
    var tv3=view.textView3
    var img=view.imageView
}

