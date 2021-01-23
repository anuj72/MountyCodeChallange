package com.example.mountyapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mountyapp.repository.MyRepository


@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val repository:MyRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            repository
        ) as T
    }
}