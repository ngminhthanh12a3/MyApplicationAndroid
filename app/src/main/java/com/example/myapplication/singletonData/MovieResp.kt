package com.example.myapplication.singletonData

data class MovieResp(val dates: Dates,
                     val page: Long,
                     val results: List<Movie>,
                     val totalPages: Long,
                     val totalResults: Long)
