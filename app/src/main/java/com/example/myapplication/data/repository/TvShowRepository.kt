package com.example.myapplication.data.repository

import com.example.myapplication.data.model.TvShow
import com.example.myapplication.data.remote.RetrofitInstance

open class TvShowRepository {

    open suspend fun getShows(): List<TvShow> {
        return RetrofitInstance.api.getShows()
    }
}