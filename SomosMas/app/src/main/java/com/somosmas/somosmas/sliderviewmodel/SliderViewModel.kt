package com.somosmas.somosmas.sliderviewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.somosmas.somosmas.APIService
import com.somosmas.somosmas.Data
import com.somosmas.somosmas.RetrofitClient
import com.somosmas.somosmas.SlideResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SliderViewModel : ViewModel() {
    private var _viewState = MutableLiveData<ViewStates>()

    val viewStates: LiveData<ViewStates>
        get() = _viewState

    private suspend fun callListSlide():SlideResponse = withContext(Dispatchers.IO) {
        val retrofit = RetrofitClient.builderRetrofit
        val call = retrofit.create(APIService::class.java)
            .getListSlider("slides")
        val slide: SlideResponse? = call.body()
        return@withContext slide!!
    }



    init {
        viewModelScope.launch {
            val slide=callListSlide()
            if(slide.success){
                _viewState.value=ViewStates.SlideResponse(slide.data)
            }else{
                _viewState.value=ViewStates.Error
            }
        }
    }
}
sealed class ViewStates{
    object Error:ViewStates()
    class SlideResponse(val data: List<Data>):ViewStates()
}
