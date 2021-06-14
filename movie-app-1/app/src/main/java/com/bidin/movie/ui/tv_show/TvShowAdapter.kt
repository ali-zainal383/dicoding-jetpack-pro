package com.bidin.movie.ui.tv_show

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bidin.movie.R
import com.bidin.movie.data.DataEntity
import com.bidin.movie.databinding.ListItemsBinding
import com.bidin.movie.ui.detail.DetailMovieActivity
import com.bidin.movie.utils.ShareButtonCallback
import com.bidin.movie.utils.TypeHelper.TYPE_TV
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TvShowAdapter(private val callback: ShareButtonCallback) : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private var listTvShow = ArrayList<DataEntity>()

    fun setTvShows(tvShows: List<DataEntity>) {
        if (tvShows == null) return
        this.listTvShow.clear()
        this.listTvShow.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemsTvShowBinding = ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class TvShowViewHolder(private val binding: ListItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: DataEntity) {
            with(binding){
                tvTitle.text = tvShow.title
                tvDescription.text = tvShow.overview
                tvRelease.text = tvShow.release
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_DATA, tvShow.id)
                    intent.putExtra(DetailMovieActivity.EXTRA_TYPE, TYPE_TV)
                    itemView.context.startActivity(intent)
                }
                imgShare.setOnClickListener { callback.onShareClick(tvShow) }
                Glide.with(itemView.context)
                        .load(tvShow.posterPath)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error))
                        .into(imgPoster)
            }
        }
    }
}