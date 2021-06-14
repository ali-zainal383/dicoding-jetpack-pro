package com.bidin.movie.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bidin.movie.R
import com.bidin.movie.data.DataEntity
import com.bidin.movie.databinding.ActivityDetailMovieBinding
import com.bidin.movie.databinding.ContentDetailMovieBinding
import com.bidin.movie.utils.TypeHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }


    private lateinit var detailContentMovieBinding: ContentDetailMovieBinding
    private lateinit var result: DataEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        detailContentMovieBinding= activityDetailMovieBinding.detailContent
        setContentView(activityDetailMovieBinding.root)

        setSupportActionBar(activityDetailMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]

        val id = intent.getStringExtra(EXTRA_DATA)
        val type = intent.getStringExtra(EXTRA_TYPE)

        when {
            type.equals(TypeHelper.TYPE_MOVIE) -> {
                setToolbarTitle(resources.getString(R.string.detail_movie))
                id?.let {
                    viewModel.setSelectedMovie(it)
                }
                result = viewModel.getDetailMovie()
            }
            type.equals(TypeHelper.TYPE_TV) -> {
                setToolbarTitle(resources.getString(R.string.detail_tv_show))
                id?.let {
                    viewModel.setSelectedTvShow(it)
                }
                result = viewModel.getDetailTvShow()
            }
        }

        detailContentMovieBinding.tvTitleDetail.text = result.title
        detailContentMovieBinding.tvReleaseDetail.text = result.release
        detailContentMovieBinding.tvDescriptionDetail.text = result.overview
        Glide.with(this)
            .load(result.backDropPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detailContentMovieBinding.imgBackdrop)
    }

    private fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }
}