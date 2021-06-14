package com.bidin.movie.ui.tv_show

import androidx.lifecycle.ViewModel
import com.bidin.movie.data.DataEntity
import com.bidin.movie.utils.DataDummy

class TvShowViewModel : ViewModel() {

    fun getTvShows(): List<DataEntity> = DataDummy.generateDummyTvShow()
}