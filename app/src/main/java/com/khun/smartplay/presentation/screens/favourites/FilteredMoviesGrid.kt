package com.khun.smartplay.presentation.screens.favourites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.khun.smartplay.data.entities.MovieList
import com.khun.smartplay.presentation.common.MovieCard
import com.khun.smartplay.presentation.common.PosterImage
import com.khun.smartplay.presentation.theme.JetStreamBottomListPadding

@Composable
fun FilteredMoviesGrid(
    state: LazyGridState,
    movieList: MovieList,
    onMovieClick: (movieId: String) -> Unit,
) {
    LazyVerticalGrid(
        state = state,
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(6),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = JetStreamBottomListPadding),
    ) {
        items(movieList, key = { it.id }) { movie ->
            MovieCard(
                onClick = { onMovieClick(movie.id) },
                modifier = Modifier.aspectRatio(1 / 1.5f),
            ) {
                PosterImage(movie = movie, modifier = Modifier.fillMaxSize())
            }
        }
    }
}
