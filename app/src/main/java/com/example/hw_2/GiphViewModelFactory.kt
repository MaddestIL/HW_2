package com.example.hw_2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GiphViewModelFactory(private val repository: GiphRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GiphVM(repository) as T
    }
}