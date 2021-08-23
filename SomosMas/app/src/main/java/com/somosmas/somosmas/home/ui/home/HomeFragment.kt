package com.somosmas.somosmas.home.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.somosmas.somosmas.R
import com.somosmas.somosmas.SliderAdapter
import com.somosmas.somosmas.SliderItem
import com.somosmas.somosmas.adapter.SliderSeccionWelcomeAdapter
import com.somosmas.somosmas.databinding.FragmentHomeBinding
import java.util.ArrayList
import kotlin.math.abs

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var sliderItemList: ArrayList<SliderItem>
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var sliderHandle: Handler
    private lateinit var sliderRun: Runnable

    private val listTitle = listOf(
        "Riendo",
        "Estirando",
        "Dia de pileta",
        "Abrazo grupal",
        "Siendo Feliz",
        "Haciendo tarea",
        "Equipo de trabajo",
        "Actividad recreativa",
        "Jugando al futbol",
        "Trabajo en equipo"
    )

    private val listDescription = listOf(
        "Siendo feliz",
        "Presentando la decoracion de la puerta",
        "Los chicos se divierten en la pileta",
        "Grupo de amigos",
        "La alegria de nuestros chicos",
        "Los niños realizando tarea",
        "Nuestros ayudantes",
        "Los niños se divierten",
        "Juego al aire libre",
        "Trabajando para hacer las tareas"
    )

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sliderItems()
        itemSliderView()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun sliderItems() {
    sliderItemList= ArrayList()
        sliderAdapter=SliderAdapter(binding.viewPager, sliderItemList,listTitle, listDescription)
        binding.viewPager.adapter=sliderAdapter
        binding.viewPager.clipToPadding= false
        binding.viewPager.clipChildren =false
        binding.viewPager.offscreenPageLimit=3
        binding.viewPager.getChildAt(0).overScrollMode= RecyclerView.OVER_SCROLL_NEVER

        val comPosPageTarn=CompositePageTransformer()
        comPosPageTarn.addTransformer(MarginPageTransformer(40))
        comPosPageTarn.addTransformer { page, position ->
            val r:Float=1 - abs(position)
            page.scaleY=0.85f + r* 0.15f
        }
        binding.viewPager.setPageTransformer(comPosPageTarn)
        sliderHandle= Handler()
        sliderRun= Runnable {
            binding.viewPager.currentItem=binding.viewPager.currentItem+1
        }
        binding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandle.removeCallbacks(sliderRun)
                    sliderHandle.postDelayed(sliderRun,4000)
                }
            }
        )
    }

    override fun onPause() {
        super.onPause()
        sliderHandle.removeCallbacks(sliderRun)
    }

    override fun onResume() {
        super.onResume()
        sliderHandle.postDelayed(sliderRun,2000)
    }

    private fun itemSliderView() {
        sliderItemList.add(SliderItem(R.drawable.foto_1))
        sliderItemList.add(SliderItem(R.drawable.foto_2))
        sliderItemList.add(SliderItem(R.drawable.foto_3))
        sliderItemList.add(SliderItem(R.drawable.foto_4))
        sliderItemList.add(SliderItem(R.drawable.foto_5))
        sliderItemList.add(SliderItem(R.drawable.foto_6))
        sliderItemList.add(SliderItem(R.drawable.foto_7))
        sliderItemList.add(SliderItem(R.drawable.foto_8))
        sliderItemList.add(SliderItem(R.drawable.foto_9))
        sliderItemList.add(SliderItem(R.drawable.foto_10))
    }
}