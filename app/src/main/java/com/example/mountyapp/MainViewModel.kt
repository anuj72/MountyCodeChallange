package com.example.mountyapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mountyapp.dto.TopRatedMovie
import com.example.mountyapp.dto.UpcomingMovie
import com.example.mountyapp.repository.MyRepository
import com.example.mountyapp.util.ApiException
import com.example.mountyapp.util.Coroutines
import com.example.mountyapp.util.NoInternetException
import java.net.SocketTimeoutException

class MainViewModel(private  var repository: MyRepository):ViewModel() {
    var movieList: MutableLiveData<TopRatedMovie>? =null
    var upcomingmovieList: MutableLiveData<UpcomingMovie>? =null

    fun getmyData(): MutableLiveData<TopRatedMovie>? {
        if (movieList == null) {
            movieList= MutableLiveData()
            FetchUpcomingMovieData()
        }
        return movieList }
    fun getmyTrendData(): MutableLiveData<UpcomingMovie>? {
        if (upcomingmovieList == null) {
            upcomingmovieList= MutableLiveData()
            FetchTrendingMovieData()
        }
        return upcomingmovieList }

    val _error = MutableLiveData<String>()
           val error=_error

    fun  FetchUpcomingMovieData(){
        Coroutines.main {
            try {
                movieList?.value=repository.TopRatedMovie()
            } catch (e: ApiException) {
                error.value= e.message.toString()
            } catch (e: NoInternetException) {
                error.value= e.message.toString()
            } catch (e: SocketTimeoutException) {
                error.value= "Network is unreachable"
            } catch (e: Exception) {
                error.value= "Sorry for inconvenience, system has encountered technical glitch" }
        }
    }

    fun  FetchTrendingMovieData(){
        Coroutines.main {
            try {
                upcomingmovieList?.value=repository.UpcomingMovie()
            } catch (e: ApiException) {
                error.value= e.message.toString()
            } catch (e: NoInternetException) {
                error.value= e.message.toString()
            } catch (e: SocketTimeoutException) {
                error.value= "Network is unreachable"
            } catch (e: Exception) {
                error.value= "Sorry for inconvenience, system has encountered technical glitch" }
        }
    }


}