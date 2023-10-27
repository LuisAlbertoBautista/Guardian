package com.example.pruebatecnicaapp.connectivity.repository



import com.example.pruebantt.connectivity.api.ApiResponse
import com.example.pruebantt.connectivity.api.ConnectionModel
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val connectionModel: ConnectionModel
): ApiResponse() {
    suspend fun simpleGet(url: String) = getResult { connectionModel.simpleGet(url) }

}