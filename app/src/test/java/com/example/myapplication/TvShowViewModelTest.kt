package com.example.myapplication

import com.example.myapplication.data.model.TvShow
import com.example.myapplication.data.repository.TvShowRepository
import com.example.myapplication.viewmodel.TvShowUiState
import com.example.myapplication.viewmodel.TvShowViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class TvShowViewModelTest {

    @Test
    fun getShows_success() = runTest {
        val fakeShows = listOf(
            TvShow(
                id = 1,
                name = "Breaking Bad",
                rating = null,
                genres = listOf("Drama"),
                summary = "Test summary",
                image = null
            )
        )

        val fakeRepository = object : TvShowRepository() {
            override suspend fun getShows(): List<TvShow> {
                return fakeShows
            }
        }

        val viewModel = TvShowViewModel(fakeRepository)

        testScheduler.advanceUntilIdle()

        val state = viewModel.uiState.value

        assertEquals(
            TvShowUiState.Success(fakeShows),
            state
        )
    }

    @Test
    fun getShows_error() = runTest {
        val fakeRepository = object : TvShowRepository() {
            override suspend fun getShows(): List<TvShow> {
                throw Exception("Gagal mengambil data")
            }
        }

        val viewModel = TvShowViewModel(fakeRepository)

        testScheduler.advanceUntilIdle()

        val state = viewModel.uiState.value

        assertEquals(
            TvShowUiState.Error("Gagal mengambil data"),
            state
        )
    }
}