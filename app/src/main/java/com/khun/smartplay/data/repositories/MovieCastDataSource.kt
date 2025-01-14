package com.khun.smartplay.data.repositories

import com.khun.smartplay.data.entities.toMovieCast
import com.khun.smartplay.data.util.AssetsReader
import com.khun.smartplay.data.util.StringConstants
import javax.inject.Inject

class MovieCastDataSource @Inject constructor(
    assetsReader: AssetsReader
) {

    private val movieCastDataReader = CachedDataReader {
        readMovieCastData(assetsReader, StringConstants.Assets.MovieCast).map {
            it.toMovieCast()
        }
    }

    suspend fun getMovieCastList() = movieCastDataReader.read()
}
