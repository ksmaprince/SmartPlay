package com.khun.smartplay.data.entities

import com.khun.smartplay.data.models.MovieCategoriesResponseItem

data class MovieCategory(
    val id: String,
    val name: String,
)

fun MovieCategoriesResponseItem.toMovieCategory(): MovieCategory =
    MovieCategory(id, name)
