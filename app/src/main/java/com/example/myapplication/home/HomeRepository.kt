package com.example.myapplication.home

import com.example.myapplication.data.FlickerData
import com.example.myapplication.data.ImageService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(private val imageService: ImageService){

    suspend fun getImages(queryText : String, pageNum : Int) : FlickerData {
        return imageService.getImages(text = queryText, page = pageNum)
    }

}