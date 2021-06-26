package com.example.myapplication.home

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.myapplication.data.ImageService
import com.example.myapplication.data.model.Photo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(private val imageService: ImageService){

    fun getImages(query: String?): LiveData<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ImagesDataSource(imageService, query) }
        ).liveData
    }

}