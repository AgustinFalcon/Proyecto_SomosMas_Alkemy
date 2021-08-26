package com.somosmas.somosmas.home.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.somosmas.somosmas.databinding.FragmentNewsBinding


class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null

    private val binding get() = _binding!!

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapterNews.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        //binding.rvFragmentNovelties.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        binding.rvFragmentNovelties.layoutManager = layoutManager

        adapter = RecyclerAdapterNews()
        binding.rvFragmentNovelties.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}