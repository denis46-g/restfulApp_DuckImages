package com.example.restfulapp.data

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AppRepository(private val duckDao: DuckDao) {

    //для извлечения с сервера
    private var DuckAPIServ = Retrofit.Builder()
        .baseUrl("https://random-d.uk/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(DuckAPIService::class.java)

    suspend fun getImages(): ArrayList<String> {
        return withContext(Dispatchers.IO) {
            val call: Call<Lst> = DuckAPIServ.getLst()
            return@withContext suspendCoroutine<ArrayList<String>> { continuation ->
                call.enqueue(object : Callback<Lst> {
                    @SuppressLint("SuspiciousIndentation")
                    override fun onResponse(call: Call<Lst>, response: Response<Lst>) {
                        if (response.isSuccessful) {
                            val lst = response.body()
                            val result: ArrayList<String> = lst!!.images
                                continuation.resume(result)
                        } else {
                            // Обработка неуспешного ответа
                            continuation.resume(ArrayList())
                        }
                    }

                    override fun onFailure(call: Call<Lst>, t: Throwable) {
                        // Обработка ошибки сетевого запроса
                        continuation.resume(ArrayList())
                    }
                })
            }
        }
    }

    suspend fun getGifs(): ArrayList<String> {
        return withContext(Dispatchers.IO) {
            val call: Call<Lst> = DuckAPIServ.getLst()
            return@withContext suspendCoroutine<ArrayList<String>> { continuation ->
                call.enqueue(object : Callback<Lst> {
                    @SuppressLint("SuspiciousIndentation")
                    override fun onResponse(call: Call<Lst>, response: Response<Lst>) {
                        if (response.isSuccessful) {
                            val lst = response.body()
                            val result: ArrayList<String> = lst!!.gifs
                            continuation.resume(result)
                        } else {
                            // Обработка неуспешного ответа
                            continuation.resume(ArrayList())
                        }
                    }

                    override fun onFailure(call: Call<Lst>, t: Throwable) {
                        // Обработка ошибки сетевого запроса
                        continuation.resume(ArrayList())
                    }
                })
            }
        }
    }

    suspend fun getHttp(): ArrayList<String> {
        return withContext(Dispatchers.IO) {
            val call: Call<Lst> = DuckAPIServ.getLst()
            return@withContext suspendCoroutine<ArrayList<String>> { continuation ->
                call.enqueue(object : Callback<Lst> {
                    @SuppressLint("SuspiciousIndentation")
                    override fun onResponse(call: Call<Lst>, response: Response<Lst>) {
                        if (response.isSuccessful) {
                            val lst = response.body()
                            val result: ArrayList<String> = lst!!.http
                            continuation.resume(result)
                        } else {
                            // Обработка неуспешного ответа
                            continuation.resume(ArrayList())
                        }
                    }

                    override fun onFailure(call: Call<Lst>, t: Throwable) {
                        // Обработка ошибки сетевого запроса
                        continuation.resume(ArrayList())
                    }
                })
            }
        }
    }

    //для базы данных
    fun getAll(): LiveData<List<Duck>> {
        return duckDao.getAll()
    }
    suspend fun insert(duck: Duck) {
        withContext(Dispatchers.IO) {
            duckDao.insert(duck)
        }
    }

    /*suspend fun update(duck: Duck) {
        withContext(Dispatchers.IO) {
            duckDao.update(duck)
        }
    }
    suspend fun delete(duck: Duck) {
        withContext(Dispatchers.IO) {
            duckDao.delete(duck)
        }
    }*/


}