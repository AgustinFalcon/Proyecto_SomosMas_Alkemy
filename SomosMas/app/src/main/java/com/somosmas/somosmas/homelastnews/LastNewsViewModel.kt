package com.somosmas.somosmas.homelastnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.somosmas.somosmas.APIService
import com.somosmas.somosmas.RetrofitClient
import com.somosmas.somosmas.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LastNewsViewModel: ViewModel() {

    private var _viewStateLastNews = MutableLiveData<ViewStateLastNews>()
    val viewStateLastNews: LiveData<ViewStateLastNews> get() = _viewStateLastNews


    private suspend fun callLastNews(): LastNewsResponse = withContext(Dispatchers.IO) {
        val retrofit = RetrofitClient.builderRetrofit
        val call = retrofit.create(APIService::class.java).getListLastNews("news")
        val lastNews: LastNewsResponse? = call.body()
        return@withContext lastNews!!
    }

    init {
        viewModelScope.launch {
            val lastNews = callLastNews()
            _viewStateLastNews.value = if (lastNews.succes.not()) {
                ViewStateLastNews.LastNewsResponse(lastNews.data.subList(0, 4))
            } else {
                ViewStateLastNews.Error
            }
        }
    }
}

sealed class ViewStateLastNews {
    object Error : ViewStateLastNews()
    class LastNewsResponse(val dataLastNews: List<DataLastNews>) : ViewStateLastNews()
}