package com.example.myapplication.home

import com.example.myapplication.data.FlickerData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
                private val homeRepository: HomeRepository
                ) : ViewModel() {

    var queryText : String? = null
    private var images  = MutableLiveData<List<Photo>>()

    fun searchImages() {
        viewModelScope.launch {
            try {
                val data : FlickerData? = queryText?.let { homeRepository.getImages(it, 1) }
                images.value = data?.photos?.photo
            } catch ( ex : Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun getImages() : LiveData<List<Photo>> {
        return images
    }

}