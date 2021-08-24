package com.somosmas.somosmas.home.ui.novelties

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.somosmas.somosmas.databinding.FragmentNoveltiesBinding


class NoveltiesFragment : Fragment() {

    private lateinit var noveltiesViewModel: NoveltiesViewModel
    private var _binding: FragmentNoveltiesBinding? = null

    private val binding get() = _binding!!

    var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        noveltiesViewModel =
            ViewModelProvider(this).get(NoveltiesViewModel::class.java)

        _binding = FragmentNoveltiesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.fragNovelties
        noveltiesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}