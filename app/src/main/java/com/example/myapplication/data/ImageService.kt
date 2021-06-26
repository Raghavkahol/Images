package com.example.myapplication.data

import com.example.myapplication.data.model.FlickerData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {

    @GET("services/rest?method=flickr.photos.getRecent&nojsoncallback=1&format=json")
    suspend fun getImages(
        @Query("api_key") apiKey: String = "062a6c0c49e4de1d78497d13a7dbb360",
        @Query("text") text: String?,
        @Query("per_page") perPage : Int = 5,
        @Query("page") page: Int
    ): FlickerData

    companion object {
        private const val BASE_URL = "https://api.flickr.com/"

        fun create(): ImageService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ImageService::class.java)
        }
    }
}
