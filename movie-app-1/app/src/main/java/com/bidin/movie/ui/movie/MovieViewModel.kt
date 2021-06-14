package com.bidin.movie.ui.movie

import androidx.lifecycle.ViewModel
import com.bidin.movie.data.DataEntity
import com.bidin.movie.utils.DataDummy

class MovieViewModel : ViewModel() {

    fun getMovies(): List<DataEntity> = DataDummy.generateDummyMovie()
}