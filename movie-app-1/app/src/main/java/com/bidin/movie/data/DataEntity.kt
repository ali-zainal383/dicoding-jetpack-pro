package com.bidin.movie.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataEntity(
    val id: String,
    val title: String,
    val overview: String,
    val release: String,
    val posterPath: String,
    val backDropPath: String,
) : Parcelable
