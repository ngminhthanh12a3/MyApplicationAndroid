package com.example.myapplication.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.singletonData.DataStore
import com.example.myapplication.singletonData.MovieID

class MovieViewModel:ViewModel() {
    val curMovie: LiveData<MovieID>
        get() = DataStore.curMovieData
}