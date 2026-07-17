package com.alfons.meonghabittracker.view

import android.R.id.selectedIcon
import android.os.Bundle
import android.se.omapi.Session
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfons.meonghabittracker.R
import com.alfons.meonghabittracker.databinding.FragmentCreateHabitBinding
import com.alfons.meonghabittracker.databinding.FragmentDashboardBinding
import com.alfons.meonghabittracker.model.Habit
import com.alfons.meonghabittracker.util.SessionManager
import com.alfons.meonghabittracker.viewmodel.DetailHabitViewModel

class CreateHabitFragment : Fragment() {
    private lateinit var viewModel: DetailHabitViewModel
    private lateinit var binding: FragmentCreateHabitBinding
    private val args: CreateHabitFragmentArgs by navArgs()
    private var existingHabit: Habit? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[DetailHabitViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val iconList = listOf("Koding", "Fitness", "Garis", "Buku", "Air")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, iconList)
        binding.spinIcon.adapter = adapter

        args.habit?.let {
            viewModel.habit.value = it
            binding.btnCreateHabit.text = "Update"
            existingHabit = it
        }

        binding.btnCreateHabit.setOnClickListener {
            val updatedHabit = viewModel.habit.value ?: return@setOnClickListener

            val selectedIcon = binding.spinIcon.selectedItem.toString()
            updatedHabit.icon = when (selectedIcon) {
                "Koding" -> "baseline_code_24"
                "Fitness" -> "baseline_directions_run_24"
                "Garis" -> "baseline_horizontal_rule_24"
                "Buku" -> "baseline_menu_book_24"
                "Air" -> "baseline_water_drop_24"
                else -> "baseline_code_24"
            }

            if (existingHabit != null) {
                viewModel.updateHabit(updatedHabit)
                Toast.makeText(context, "Habit Berhasil Diupdate!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addHabit(listOf(updatedHabit))
                Toast.makeText(context, "Habit Baru Ditambahkan!", Toast.LENGTH_SHORT).show()
            }
            findNavController().popBackStack()
        }
    }
}