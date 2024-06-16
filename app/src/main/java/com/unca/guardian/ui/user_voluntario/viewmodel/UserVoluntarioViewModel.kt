package com.unca.guardian.ui.user_victima.viewmodel

import android.graphics.ColorSpace.Model
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unca.guardian.connectivity.api.NetworkResponse
import com.unca.guardian.connectivity.api.dataAccess
import com.unca.guardian.connectivity.repository.ApiRepository
import com.unca.pruebatecnicaapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserVoluntarioViewModel @Inject constructor(private val api: ApiRepository) : ViewModel() {
    private var data: MutableLiveData<Model> = MutableLiveData()
    private var error: MutableLiveData<String> = MutableLiveData()

    fun getData(owner: LifecycleOwner) {
        val respuestaObservable = dataAccess { api.simpleGet(Constants.BASE_URL +"api")}
        respuestaObservable.observe(owner){
                response ->
            when (response.status) {
                NetworkResponse.Status.LOADING -> {
                }
                NetworkResponse.Status.SUCCESS -> {
                    response.data?.let {
                        try {
                        }catch (e: Throwable){
                            e.printStackTrace()
                        }
                    }
                }
                NetworkResponse.Status.ERROR -> {
                    Log.d(Constants.TAG, response.toString())
                }
            }
        }
        return
    }
}