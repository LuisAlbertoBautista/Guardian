package com.example.pruebantt.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.pruebantt.connectivity.api.NetworkResponse
import com.example.pruebantt.databinding.ActivityMainBinding
import com.example.pruebantt.models.UserRamdon
import com.example.pruebantt.ui.user.viewmodel.UserViewModel
import com.example.pruebatecnicaapp.utils.Constants.TAG
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var vm: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnLoadNewUser.setOnClickListener(){
            getUserRamdon()
        }
        getUserRamdon()
    }

    fun getUserRamdon(){
        vm.getUserRamdon().observe(this){
                response ->
            when (response.status) {
                NetworkResponse.Status.LOADING -> {
                }
                NetworkResponse.Status.SUCCESS -> {
                    response.data?.let {
                        val userRamdon = Gson().fromJson(it.string(), UserRamdon::class.java)
                        try {
                            loadData(userRamdon)
                        }catch (e: Throwable){
                            e.printStackTrace()
                        }
                    }
                }
                NetworkResponse.Status.ERROR -> {
                    Log.d(TAG, response.toString())
                }
            }
        }
    }

    fun loadData(userRamdon: UserRamdon){
        Picasso.with(this).load(userRamdon.results[0].picture.large).into(binding.imgPerfil)
        val infoUser = "Nombre: ${userRamdon.results[0].name}\n Email: ${userRamdon.results[0].email}\n Cumple: ${userRamdon.results[0].dob.date}\n Direccion: ${userRamdon.results[0].location.street}, ${userRamdon.results[0].location.city}, ${userRamdon.results[0].location.state}, ${userRamdon.results[0].location.country}\n Telefono: ${userRamdon.results[0].cell}\n Password: ${userRamdon.results[0].login.password}"
        binding.tvInfoUser.text = infoUser
    }

}