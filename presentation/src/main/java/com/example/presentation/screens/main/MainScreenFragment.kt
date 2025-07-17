package com.example.presentation.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.databinding.FragmentMainScreenBinding
import com.example.presentation.viewmodels.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainScreenFragment : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainScreenViewModel by viewModels()

    private lateinit var adapter: MainScreenCourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MainScreenCourseAdapter(emptyList())
        binding.courseListView.adapter = adapter
        binding.courseListView.layoutManager = LinearLayoutManager(requireContext())

        observeCourses()
    }

    private fun observeCourses() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.courses.collectLatest { courses ->
                adapter.updateData(courses)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}