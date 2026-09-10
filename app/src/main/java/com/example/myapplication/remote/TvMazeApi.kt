package com.example.myapplication.data.remote

import com.example.myapplication.data.model.TvShow
import retrofit2.http.GET

interface TvMazeApi {

    @GET("shows?page=1")
    suspend fun getShows(): List<TvShow>
}