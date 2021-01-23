package com.example.mountyapp.repository

import com.example.mountyapp.dto.TopRatedMovie
import com.example.mountyapp.dto.UpcomingMovie
import com.example.mountyapp.networking.MyApi
import com.example.mountyapp.networking.SafeApiRequest

class MyRepository(private val myApi: MyApi

): SafeApiRequest() {

    var apikey="df5ebda0627f2f0f8fb41ad8a1659ba3"
    var language="language=en-US"

    suspend fun TopRatedMovie():TopRatedMovie{
        return apiRequest { myApi.TopRatedMovie(apikey,language,"1") }
    }

    suspend fun UpcomingMovie():UpcomingMovie{
        return apiRequest { myApi.UpcomingMovie(apikey,language,"1") }
    }
}

