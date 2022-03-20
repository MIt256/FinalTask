package com.example.finaltask.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finaltask.models.Item
import com.example.finaltask.models.Post
import com.example.finaltask.models.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repo: Repository): ViewModel()  {

    private var data: MutableLiveData<Item> = MutableLiveData()
    private var res: MutableLiveData<String> = MutableLiveData()

    init {
        getData()
    }

    fun getAnswer() = res

    fun getLiveData() = data

    fun getData(){
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {data.value = repo.getData()}
        }
    }

    fun setData() {
        var ans: String
        CoroutineScope(Dispatchers.IO).launch {
            val map = mapOf("text" to "hello", "numeric" to "2.5", "list" to "v1")
            withContext(Dispatchers.Main) { res.value = repo.setData(Post(map)).toString() }
        }
    }

}