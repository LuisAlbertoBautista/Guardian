package com.unca.guardian.connectivity.repository



import com.unca.guardian.connectivity.api.ApiResponse
import com.unca.guardian.connectivity.api.ConnectionModel
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val connectionModel: ConnectionModel
): ApiResponse() {
    suspend fun simpleGet(url: String) = getResult { connectionModel.simpleGet(url) }

}