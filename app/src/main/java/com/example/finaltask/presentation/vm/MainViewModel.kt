package com.example.finaltask.presentation.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finaltask.constants.Constants.Companion.ERROR
import com.example.finaltask.constants.Constants.Companion.WRONG
import com.example.finaltask.data.models.Field
import com.example.finaltask.data.models.Item
import com.example.finaltask.domain.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repo: Repository): ViewModel()  {

    private var data: MutableLiveData<Item> = MutableLiveData()
    private var res:String?=""

    fun isEmpty()=
        data.value == null

    fun getAnswer() = res

    fun getLiveData() = data

    fun getForm(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
            withContext(Dispatchers.Main) {
                data.value = repo.getData()
            }
            } catch (ex:Exception){Log.e(ERROR,ex.toString())}
        }
    }

   suspend fun sendForm(dataToSend: ArrayList<Field>) {
       try {
       withContext(Dispatchers.Main) { res = repo.setData(dataToSend)?.result }
       } catch (ex:Exception){
           Log.e(ERROR,ex.toString())
           res = WRONG
       }
   }


}