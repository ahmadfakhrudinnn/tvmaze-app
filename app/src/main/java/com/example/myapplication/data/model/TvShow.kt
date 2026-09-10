package com.example.myapplication.data.model

data class TvShow(
    val id: Int,
    val name: String,
    val rating: Rating?,
    val genres: List<String>,
    val summary: String?,
    val image: Image?
)

data class Rating(
    val average: Double?
)

data class Image(
    val medium: String?,
    val original: String?
)