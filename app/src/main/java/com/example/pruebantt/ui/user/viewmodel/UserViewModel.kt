package com.example.pruebantt.ui.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pruebantt.connectivity.api.NetworkResponse
import com.example.pruebantt.connectivity.api.dataAccess
import com.example.pruebatecnicaapp.connectivity.repository.ApiRepository
import com.example.pruebatecnicaapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val api: ApiRepository) : ViewModel() {
    fun getUserRamdon(): LiveData<NetworkResponse<ResponseBody>> {
        return dataAccess { api.simpleGet(Constants.BASE_URL +"api")}
    }
}