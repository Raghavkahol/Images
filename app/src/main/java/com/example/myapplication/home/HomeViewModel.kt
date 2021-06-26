package com.example.myapplication.home

import androidx.lifecycle.*
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
                private val homeRepository: HomeRepository
                ) : ViewModel() {

    var queryText : String? = DEFAULT_QUERY

    fun searchImages() {
       currentQuery.value = queryText
    }

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val photos = currentQuery.switchMap { queryString ->
        homeRepository.getImages(queryString).cachedIn(viewModelScope)
    }

    companion object {
        private const val DEFAULT_QUERY = "CAT"
    }

}