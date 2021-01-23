package com.example.mountyapp.networking

import com.example.mountyapp.dto.TopRatedMovie
import com.example.mountyapp.dto.UpcomingMovie
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit


interface MyApi {
    @GET("movie/top_rated")
    @Headers("Content-Type: application/json")
    suspend fun TopRatedMovie(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: String,) : Response<TopRatedMovie>


    @GET("movie/upcoming")
    @Headers("Content-Type: application/json")
    suspend fun UpcomingMovie(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: String,) : Response<UpcomingMovie>

    companion object{
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor) : MyApi{
            var baseurl="https://api.themoviedb.org/3/"
            val okHttpclient= OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(networkConnectionInterceptor).build()
            return Retrofit.Builder()
                .client(okHttpclient)
                .baseUrl(baseurl)
                .addConverterFactory(NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }

}


