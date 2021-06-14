package com.bidin.movie.ui.detail

import androidx.lifecycle.ViewModel
import com.bidin.movie.data.DataEntity
import com.bidin.movie.utils.DataDummy

class DetailViewModel : ViewModel(){

    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun setSelectedTvShow(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    private fun getMovies(): List<DataEntity> = DataDummy.generateDummyMovie()
    private fun getTvShows(): List<DataEntity> = DataDummy.generateDummyTvShow()

    fun getDetailMovie(): DataEntity {
        lateinit var movie: DataEntity
        val moviesEntities = getMovies()
        for (movieEntity in moviesEntities) {
            if (movieEntity.id == movieId) {
                movie = movieEntity
            }
        }
        return movie
    }

    fun getDetailTvShow(): DataEntity {
        lateinit var tvShow: DataEntity
        val tvShowEntities = getTvShows()
        for (tvShowEntity in tvShowEntities) {
            if (tvShowEntity.id == tvShowId) {
                tvShow = tvShowEntity
            }
        }
        return tvShow
    }
}