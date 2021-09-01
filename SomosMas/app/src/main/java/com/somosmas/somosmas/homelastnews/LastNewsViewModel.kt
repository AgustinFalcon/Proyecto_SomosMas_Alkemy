package com.somosmas.somosmas.homelastnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.somosmas.somosmas.APIService
import com.somosmas.somosmas.RetrofitClient
import com.somosmas.somosmas.sliderviewmodel.ViewStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LastNewsViewModel: ViewModel() {
    private var _viewState = MutableLiveData<ViewStates>()
    private var _viewStateLastNews = MutableLiveData<ViewStateLastNews>()

    val viewStates: LiveData<ViewStates> get() = _viewState
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
            if (lastNews.succes) {
                _viewStateLastNews.value = ViewStateLastNews.LastNewsResponse(lastNews.data)
            } else {
                _viewStateLastNews.value = ViewStateLastNews.Error
            }
        }
    }
}


sealed class ViewStateLastNews {
    object Error : ViewStateLastNews()
    class LastNewsResponse(val dataLastNews: List<DataLastNews>) : ViewStateLastNews()
}