package com.khun.smartplay.data.repositories

import com.khun.smartplay.data.entities.Movie
import com.khun.smartplay.data.models.MovieCastResponseItem
import com.khun.smartplay.data.models.MovieCategoriesResponseItem
import com.khun.smartplay.data.models.MoviesResponseItem
import com.khun.smartplay.data.util.AssetsReader
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

internal class CachedDataReader<T>(private val reader: suspend () -> List<T>) {
    private val mutex = Mutex()
    private lateinit var cache: List<T>

    suspend fun read(): List<T> {
        mutex.withLock {
            if (!::cache.isInitialized) {
                cache = reader()
            }
        }
        return cache
    }
}

internal typealias MovieDataReader = CachedDataReader<Movie>

internal suspend fun readMovieData(
    assetsReader: AssetsReader,
    resourceId: String,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
): List<MoviesResponseItem> = withContext(dispatcher) {
    assetsReader.getJsonDataFromAsset(resourceId).map {
        Json.decodeFromString<List<MoviesResponseItem>>(it)
    }.getOrDefault(emptyList())
}

internal suspend fun readMovieCastData(
    assetsReader: AssetsReader,
    resourceId: String,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
): List<MovieCastResponseItem> = withContext(dispatcher) {
    assetsReader.getJsonDataFromAsset(resourceId).map {
        Json.decodeFromString<List<MovieCastResponseItem>>(it)
    }.getOrDefault(emptyList())
}

internal suspend fun readMovieCategoryData(
    assetsReader: AssetsReader,
    resourceId: String,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
): List<MovieCategoriesResponseItem> = withContext(dispatcher) {
    assetsReader.getJsonDataFromAsset(resourceId).map {
        Json.decodeFromString<List<MovieCategoriesResponseItem>>(it)
    }.getOrDefault(emptyList())
}
