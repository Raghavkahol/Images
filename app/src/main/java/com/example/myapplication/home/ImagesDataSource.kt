package com.example.myapplication.home

import androidx.paging.PagingSource
import com.bumptech.glide.load.HttpException
import com.example.myapplication.data.FlickerData
import com.example.myapplication.data.ImageService
import com.example.myapplication.data.Photo
import java.io.IOException

class ImagesDataSource(private val imageService : ImageService, private val queryString : String?)
    : PagingSource<Int, Photo>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: 1
        return try {
            val response = imageService.getImages(text = queryString, page = position)
            val nextKey = position + 1
            LoadResult.Page(
                data = response.photos.photo,
                prevKey = if (position == 1) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}