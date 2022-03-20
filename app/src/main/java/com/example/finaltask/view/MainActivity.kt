package com.example.finaltask.view

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.finaltask.R
import com.example.finaltask.databinding.ActivityMainBinding
import com.example.finaltask.di.application.MyApplication
import com.example.finaltask.vm.MainViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel by lazy {
        ViewModelProvider( this, vmFactory).get(MainViewModel::class.java)
    }
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.recycler.layoutManager = LinearLayoutManager(this)

        (application as MyApplication).getApplicationComponent().inject(this)

        CoroutineScope(Dispatchers.Default).launch {
            if (mainViewModel.isEmpty()) {
                binding.progressBar.visibility = View.VISIBLE
                while (!checkConnectivity())
                    delay(5000)
                mainViewModel.getForm()

            }
        }

        mainViewModel.getLiveData().observe(this, {
            it?.let {
                adapter.addToList(it.fields)
                supportActionBar?.title = it.title
                Glide.with(this)
                    .load(it.image)
                    .into(binding.imageView)
            }
            binding.progressBar.visibility = View.INVISIBLE
        })

        binding.actionButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE

            CoroutineScope(Dispatchers.IO).launch {
                mainViewModel.sendForm(adapter.getAnswers())
                //dialog
                runOnUiThread { binding.progressBar.visibility = View.INVISIBLE }
                runOnUiThread {
                    AlertDialog.Builder(binding.root.context)
                        .setMessage(mainViewModel.getAnswer().value)
                        .setPositiveButton(R.string.ok, null)
                        .create()
                        .show()
                }
            }
        }
        adapter = RecyclerAdapter()
        binding.recycler.adapter = adapter
    }

    private fun checkConnectivity(): Boolean {
        val connectManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectManager.activeNetwork != null) return true
        return false
    }
}