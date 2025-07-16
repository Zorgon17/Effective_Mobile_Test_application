package com.example.presentation.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.databinding.FragmentMainScreenBinding
import com.example.presentation.models.Course
import com.example.presentation.viewmodels.MainScreenViewModel

class MainScreenFragment : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val courses = listOf(
            Course(
                id = 1,
                title = "Java-разработчик с нуля",
                text = "Освойте backend-разработку на Java",
                price = "999 ₽",
                rate = 4.9,
                startDate = "22 Мая 2024",
                hasLike = true,
                publishDate = "2024-05-01"
            ),
            Course(
                id = 2,
                title = "3D-дженералист",
                text = "Создавай 3D-миры, модели и анимацию",
                price = "12 000 ₽",
                rate = 4.7,
                startDate = "10 Сентября 2024",
                hasLike = false,
                publishDate = "2024-04-28"
            )
        )

        val adapter = MainScreenCourseAdapter(courses)
        binding.courseListView.adapter = adapter
        binding.courseListView.layoutManager = LinearLayoutManager(requireContext())
    }
}