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
    private var dataToSend =  mutableMapOf<String,String>()

    fun isEmpty()=
        data.value == null

    fun getAnswer() = res

    fun getLiveData() = data

    fun getForm(){
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {data.value = repo.getData()
                repo.getData()?.fields?.forEach{
                    dataToSend += it.name to ""
                }
            }
        }
    }

    suspend fun sendForm(answers: Map<String, String>) {
        dataToSend.putAll(answers)
        withContext(Dispatchers.Main) { res.value = repo.setData(Post(dataToSend))?.result }
    }

}