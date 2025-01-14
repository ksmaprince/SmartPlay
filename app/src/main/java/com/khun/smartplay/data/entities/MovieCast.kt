package com.khun.smartplay.data.entities

import com.khun.smartplay.data.models.MovieCastResponseItem

data class MovieCast(
    val id: String,
    val characterName: String,
    val realName: String,
    val avatarUrl: String
)

fun MovieCastResponseItem.toMovieCast(): MovieCast =
    MovieCast(
        id,
        characterName,
        realName,
        avatarUrl
    )
