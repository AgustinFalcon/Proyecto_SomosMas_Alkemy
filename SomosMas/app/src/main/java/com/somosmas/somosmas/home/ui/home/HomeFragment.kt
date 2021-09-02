package com.somosmas.somosmas.home.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.somosmas.somosmas.*
import com.somosmas.somosmas.adapters.SliderAdapter
import com.somosmas.somosmas.adapters.SliderTestimonyAdapter
import com.somosmas.somosmas.databinding.FragmentHomeBinding
import com.somosmas.somosmas.homelastnews.AdapterRecyclerLastNews
import com.somosmas.somosmas.homelastnews.DataLastNews
import com.somosmas.somosmas.homelastnews.LastNewsViewModel
import com.somosmas.somosmas.homelastnews.ViewStateLastNews
import com.somosmas.somosmas.sliderviewmodel.SliderViewModel
import com.somosmas.somosmas.sliderviewmodel.ViewStateTestimony
import com.somosmas.somosmas.sliderviewmodel.ViewStates
import kotlin.math.abs


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var sliderAdapter: SliderAdapter
    private var sliderHandle: Handler = Handler()
    private lateinit var sliderRun: Runnable
    private lateinit var viewModel: SliderViewModel
    private lateinit var listData: MutableList<Data>
    private lateinit var listTestimony: MutableList<DataTestimony>
    private lateinit var testimonyAdapter: SliderTestimonyAdapter
    private var binding: FragmentHomeBinding? = null

    private lateinit var viewModelLastNews: LastNewsViewModel
    private lateinit var listLastNews: MutableList<DataLastNews>
    private lateinit var lastNewsAdapter: AdapterRecyclerLastNews


    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = FragmentHomeBinding.inflate(inflater, container, false)


        viewModel = ViewModelProvider(this).get(SliderViewModel::class.java)
        viewModel.viewStates.observe(::getLifecycle, ::handleViewStates)

        viewModel.viewStatesTestimony.observe(::getLifecycle, ::handleTestimonyViewStates)
        viewModel.viewStates.observe(::getLifecycle, ::handleViewStates)

        viewModelLastNews = ViewModelProvider(this).get(LastNewsViewModel::class.java)
        viewModelLastNews.viewStateLastNews.observe(::getLifecycle, ::handleLastNewsViewStates)

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    private fun sliderItems() {
        binding?.apply {
            with(viewPager) {
                sliderAdapter = SliderAdapter(this, listData)
                this.adapter = sliderAdapter
                this.clipToPadding = false
                this.clipChildren = false
                this.offscreenPageLimit = 3
                this.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                val comPosPageTarn = CompositePageTransformer()
                comPosPageTarn.addTransformer(MarginPageTransformer(40))
                comPosPageTarn.addTransformer { page, position ->
                    val r: Float = 1 - abs(position)
                    page.scaleY = 0.85f + r * 0.15f
                }
                this.setPageTransformer(comPosPageTarn)
                sliderRun = Runnable {
                    this.currentItem = this.currentItem + 1
                }
                this.registerOnPageChangeCallback(
                    object : ViewPager2.OnPageChangeCallback() {
                        override fun onPageSelected(position: Int) {
                            super.onPageSelected(position)
                            sliderHandle.removeCallbacks(sliderRun)
                            sliderHandle.postDelayed(sliderRun, 4000)
                        }
                    }
                )
            }
        }
    }

    private fun handleViewStates(slideResponse: ViewStates) {
        when (slideResponse) {
            is ViewStates.Error -> {
            }
            is ViewStates.SlideResponse -> {
                listData = slideResponse.data as MutableList<Data>
                sliderItems()
            }
        }
    }

    private fun handleTestimonyViewStates(testimonyResponse: ViewStateTestimony) {
        when (testimonyResponse) {
            is ViewStateTestimony.Error -> {

            }
            is ViewStateTestimony.TestimonyResponse -> {
                listTestimony = testimonyResponse.dataTestimony as MutableList<DataTestimony>
                testimonyAdapter = SliderTestimonyAdapter(listTestimony)
                binding?.rvTestimony?.adapter = testimonyAdapter
            }
        }
    }


    private fun handleLastNewsViewStates(lastNewsResponse: ViewStateLastNews) {
        when (lastNewsResponse) {
            is ViewStateLastNews.Error -> {
                //msjError
            }

            is ViewStateLastNews.LastNewsResponse -> {
                listLastNews = lastNewsResponse.dataLastNews as MutableList<DataLastNews>
                lastNewsAdapter = AdapterRecyclerLastNews(listLastNews)
                binding?.rvHomeUltimasNovedades?.adapter = lastNewsAdapter

            }
        }

    }
}