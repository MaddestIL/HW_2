package com.example.hw_2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GiphVM(private var repository: GiphRepository) : ViewModel() {

    private val _giphUiState = MutableStateFlow(emptyList<POJO.Data>())
    val giphState = _giphUiState.asStateFlow()

    fun getAllGiphs() {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler got $exception")
        }) {
            repository.getDataList()
                .collect { giphs ->
                    _giphUiState.value = giphs
                }
        }
    }
}
//    val GiphDataList: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
//    val giphState = GiphDataList.asStateFlow()
//    fun getDataList() = viewModelScope.launch {
//
//        GiphDataList.value = ApiState.Loading
//        repository.getDataList()
//            .catch { e ->
//                    GiphDataList.value = ApiState.Failure(e)
//            }.collect{ data ->
//                GiphDataList.value = ApiState.Success(data)
//
//            }
//
//    }