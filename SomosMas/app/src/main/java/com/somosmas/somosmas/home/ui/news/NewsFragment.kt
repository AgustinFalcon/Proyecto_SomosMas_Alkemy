package com.somosmas.somosmas.home.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.somosmas.somosmas.databinding.FragmentNewsBinding


class NewsFragment : Fragment() {

    private var binding: FragmentNewsBinding? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapterNews.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)

        //binding.rvFragmentNovelties.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        binding?.rvFragmentNovelties?.layoutManager = layoutManager

        adapter = RecyclerAdapterNews()
        binding?.rvFragmentNovelties?.adapter = adapter

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}