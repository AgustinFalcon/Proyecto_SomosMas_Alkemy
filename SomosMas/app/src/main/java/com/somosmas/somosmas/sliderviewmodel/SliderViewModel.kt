package com.somosmas.somosmas.sliderviewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.somosmas.somosmas.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SliderViewModel : ViewModel() {
    private var _viewState = MutableLiveData<ViewStates>()

    private var _viewStateTestimony = MutableLiveData<ViewStateTestimony>()

    val viewStates: LiveData<ViewStates>
        get() = _viewState

    val viewStatesTestimony: LiveData<ViewStateTestimony>
        get() = _viewStateTestimony

    private suspend fun callListSlide(): SlideResponse = withContext(Dispatchers.IO) {
        val retrofit = RetrofitClient.builderRetrofit
        val call = retrofit.create(APIService::class.java)
            .getListSlider("slides")
        val slide: SlideResponse? = call.body()
        return@withContext slide!!
    }

    init {
        viewModelScope.launch {
            val slide = callListSlide()
            _viewState.value = if (slide.success) {
                ViewStates.SlideResponse(slide.data)
            } else {
                ViewStates.Error
            }
        }
    }

    private suspend fun callListSlideTestimony(): TestimonyResponse = withContext(Dispatchers.IO) {
        val retrofit = RetrofitClient.builderRetrofit
        val call = retrofit.create(APIService::class.java).getListSliderTestimony("testimonials")
        val testimony: TestimonyResponse? = call.body()
        return@withContext testimony!!
    }

    init {
        viewModelScope.launch {
            val testimony = callListSlideTestimony()
            _viewStateTestimony.value = if (testimony.success) {
                ViewStateTestimony.TestimonyResponse(testimony.data.subList(0, 4))
            } else {
                ViewStateTestimony.Error
            }
        }
    }

}

sealed class ViewStates {
    object Error : ViewStates()
    class SlideResponse(val data: List<Data>) : ViewStates()
}

sealed class ViewStateTestimony {
    object Error : ViewStateTestimony()
    class TestimonyResponse(val dataTestimony: List<DataTestimony>) : ViewStateTestimony()
}
