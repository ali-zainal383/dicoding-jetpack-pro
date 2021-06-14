package com.bidin.movie.utils

import com.bidin.movie.data.DataEntity

interface ShareButtonCallback {
    fun onShareClick(data: DataEntity)
}
