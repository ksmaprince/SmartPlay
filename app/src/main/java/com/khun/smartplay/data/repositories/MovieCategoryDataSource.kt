package com.khun.smartplay.data.repositories

import com.khun.smartplay.data.entities.toMovieCategory
import com.khun.smartplay.data.util.AssetsReader
import com.khun.smartplay.data.util.StringConstants
import javax.inject.Inject

class MovieCategoryDataSource @Inject constructor(
    assetsReader: AssetsReader
) {

    private val movieCategoryDataReader = CachedDataReader {
        readMovieCategoryData(assetsReader, StringConstants.Assets.MovieCategories).map {
            it.toMovieCategory()
        }
    }

    suspend fun getMovieCategoryList() = movieCategoryDataReader.read()
}
