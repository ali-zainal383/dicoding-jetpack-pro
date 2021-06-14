package com.bidin.movie.ui.detail

import com.bidin.movie.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovie()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShow()[0]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
        viewModel.setSelectedMovie(movieId)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getDetailMovie() {
        viewModel.setSelectedMovie(movieId)
        val movie = viewModel.getDetailMovie()
        assertNotNull(movie)
        assertEquals(dummyMovie.title, movie.title)
        assertEquals(dummyMovie.release, movie.release)
        assertEquals(dummyMovie.overview, movie.overview)
        assertEquals(dummyMovie.backDropPath, movie.backDropPath)
    }

    @Test
    fun getDetailTvShow() {
        viewModel.setSelectedTvShow(tvShowId)
        val tvShow = viewModel.getDetailTvShow()
        assertNotNull(tvShow)
        assertEquals(dummyTvShow.title, tvShow.title)
        assertEquals(dummyTvShow.release, tvShow.release)
        assertEquals(dummyTvShow.overview, tvShow.overview)
        assertEquals(dummyTvShow.backDropPath, tvShow.backDropPath)
    }
}