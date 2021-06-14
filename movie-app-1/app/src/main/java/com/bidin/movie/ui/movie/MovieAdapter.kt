package com.bidin.movie.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bidin.movie.R
import com.bidin.movie.data.DataEntity
import com.bidin.movie.databinding.ListItemsBinding
import com.bidin.movie.ui.detail.DetailMovieActivity
import com.bidin.movie.utils.ShareButtonCallback
import com.bidin.movie.utils.TypeHelper.TYPE_MOVIE
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieAdapter(private val callback: ShareButtonCallback) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovies = ArrayList<DataEntity>()

    fun setMovies(movies: List<DataEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding = ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class MovieViewHolder(private val binding: ListItemsBinding ) : RecyclerView.ViewHolder(binding.root){
        fun bind(movie: DataEntity){
            with(binding){
                tvTitle.text = movie.title
                tvDescription.text = movie.overview
                tvRelease.text = movie.release
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_DATA, movie.id)
                    intent.putExtra(DetailMovieActivity.EXTRA_TYPE, TYPE_MOVIE)
                    itemView.context.startActivity(intent)
                }
                imgShare.setOnClickListener { callback.onShareClick(movie) }
                Glide.with(itemView.context)
                        .load(movie.posterPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(imgPoster)
            }
        }
    }
}