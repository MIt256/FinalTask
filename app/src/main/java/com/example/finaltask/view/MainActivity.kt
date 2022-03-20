package com.example.finaltask.view

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.finaltask.R
import com.example.finaltask.databinding.ActivityMainBinding
import com.example.finaltask.di.application.MyApplication
import com.example.finaltask.vm.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel by lazy { ViewModelProvider(this,vmFactory).get(MainViewModel::class.java) }
    lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.recycler.layoutManager = LinearLayoutManager(this)

        (application as MyApplication).getApplicationComponent().inject(this)

//        CoroutineScope(Dispatchers.IO).launch {
//            if (mainViewModel.isListEmpty()) {
//                while (!checkConnectivity())
//                    delay(5000)
//                mainViewModel.getData()
//            }
//        }

        //dialog
        mainViewModel.getAnswer().observe(this, {
            AlertDialog.Builder(binding.root.context).setMessage(it)
                .setPositiveButton("OK", null)
                .create()
                .show()
        })

        mainViewModel.getLiveData().observe(this, {
            it?.let {
                adapter.addToList(it.fields)

                Glide.with(this)
                    .load(it.image)
                    .into(binding.imageView)
            }
        })

        binding.actionButton.setOnClickListener {

            mainViewModel.setData(adapter.getAnswers())
        }

        adapter = RecyclerAdapter()
        binding.recycler.adapter = adapter

    }
}