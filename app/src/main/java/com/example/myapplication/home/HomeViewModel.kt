package com.example.myapplication.home

import androidx.lifecycle.*
import com.example.myapplication.data.FlickerData
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.data.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
                private val homeRepository: HomeRepository
                ) : ViewModel() {

    var queryText : String? = null

    fun searchImages() {
       currentQuery.value = queryText
    }

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val photos = currentQuery.switchMap { queryString ->
        homeRepository.getImages(queryString).cachedIn(viewModelScope)
    }

    companion object {
        private const val DEFAULT_QUERY = ""
    }

}