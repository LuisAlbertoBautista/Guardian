package com.unca.guardian.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.unca.guardian.databinding.ActivityMainBinding
import com.unca.guardian.ui.user_victima.viewmodel.UserVictimaViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var vm: UserVictimaViewModel
    private lateinit var owner: LifecycleOwner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vm = ViewModelProvider(this).get(UserVictimaViewModel::class.java)
        owner=this

        binding.btnLoadNewUser.setOnClickListener(){
            getData()
        }
    }

    fun getData(){
        vm.getData(owner)
    }

    fun loadData(){
    }
}