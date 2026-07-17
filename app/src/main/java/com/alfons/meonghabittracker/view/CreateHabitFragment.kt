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
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfons.meonghabittracker.R
import com.alfons.meonghabittracker.databinding.FragmentCreateHabitBinding
import com.alfons.meonghabittracker.databinding.FragmentDashboardBinding
import com.alfons.meonghabittracker.model.Habit
import com.alfons.meonghabittracker.util.SessionManager
import com.alfons.meonghabittracker.viewmodel.DetailHabitViewModel

class CreateHabitFragment : Fragment() {
    private lateinit var viewModel: DetailHabitViewModel
    private val habitListAdapter = HabitListAdapter(arrayListOf())
    private lateinit var binding: FragmentCreateHabitBinding

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


        val iconList = listOf("Koding", "Fitness", "Garis", "Buku", "Air")
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, iconList)
        binding.spinIcon.adapter = adapter

        val selectedIcon = binding.spinIcon.selectedItem.toString()
        var namaAsliIcon = ""
        when (selectedIcon) {
            "Koding" -> {
                namaAsliIcon = "baseline_code_24"
            }

            "Fitness" -> {
                namaAsliIcon = "baseline_directions_run_24"
            }

            "Garis" -> {
                namaAsliIcon = "baseline_horizontal_rule_24"
            }

            "Buku" -> {
                namaAsliIcon = "baseline_menu_book_24"
            }

            "Air" -> {
                namaAsliIcon = "baseline_water_drop_24"
            }
        }

        val userId = SessionManager.getUserId(requireContext())

        binding.btnCreateHabit.setOnClickListener {

            if (userId != -1){
                val habit = Habit(
                    0,
                    binding.txtHabitName.text.toString(),
                    binding . txtDescription . text . toString (),
                    binding . txtGoal . text . toString ().toIntOrNull() ?: 0,
                    0,
                    binding.txtUnit.text.toString(),
                    namaAsliIcon,
                    ownerId = userId
                )
                val list = listOf(habit)
                viewModel.addHabit(list)

                Toast.makeText(context, "Habit Baru Ditambahkan!", Toast.LENGTH_SHORT).show()
                it.findNavController().popBackStack()
            } else {
            Toast.makeText(context, "Tidak Berhasil! User tidak ditemukan", Toast.LENGTH_SHORT).show()
        }

        }
    }
}