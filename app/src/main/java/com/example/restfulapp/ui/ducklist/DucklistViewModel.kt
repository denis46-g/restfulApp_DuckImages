package com.example.restfulapp.ui.ducklist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restfulapp.data.AppDatabase
import com.example.restfulapp.data.AppRepository
import com.example.restfulapp.data.Duck
import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.internal.NopCollector.emit
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DucklistViewModel(application: Application) : AndroidViewModel(application) {

    val duckDao = AppDatabase.getDataBase(application).duckDao()
    val repository : AppRepository = AppRepository(duckDao)


    val ducks: LiveData<List<Duck>>


    val images : MutableLiveData<ArrayList<String>> =  MutableLiveData<ArrayList<String>>()
    val gifs : MutableLiveData<ArrayList<String>> = MutableLiveData<ArrayList<String>>()
    val http : MutableLiveData<ArrayList<String>> = MutableLiveData<ArrayList<String>>()

    //для извлечения с сервера
    fun GetImages(): MutableLiveData<ArrayList<String>> {
        viewModelScope.launch {
            val data = repository.getImages()
            images.value = data
        }
        return images
    }

    fun GetGifs(): MutableLiveData<ArrayList<String>> {
        viewModelScope.launch {
            val data = repository.getGifs()
            gifs.value = data
        }
        return gifs
    }

    fun GetHttp(): MutableLiveData<ArrayList<String>> {
        viewModelScope.launch {
            val data = repository.getHttp()
            http.value = data
        }
        return http
    }

    //для базы данных
    init{
        ducks = repository.getAll()
    }
    fun insert(duck: Duck) = viewModelScope.launch {
        repository.insert(duck)
    }
}