package com.alfons.meonghabittracker.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.Navigation
import com.alfons.meonghabittracker.R
import com.alfons.meonghabittracker.databinding.FragmentCreateHabitBinding
import com.alfons.meonghabittracker.databinding.FragmentDashboardBinding
import com.alfons.meonghabittracker.model.GlobalData
import com.alfons.meonghabittracker.model.Habit

class CreateHabitFragment : Fragment() {
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

        val iconList = listOf("Koding", "Fitness", "Garis", "Buku", "Air")
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, iconList)
        binding.spinIcon.adapter = adapter
        //tes
        binding.btnCreateHabit.setOnClickListener {
            val name = binding.txtHabitName.text.toString()
            val desc = binding.txtDescription.text.toString()
            val goal = binding.txtGoal.text.toString().toIntOrNull() ?: 0
            val unit = binding.txtUnit.text.toString()
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

            val newId = (GlobalData.habits.size + 1).toString()
            val newHabit = Habit(newId, name, desc, goal, 0, unit, namaAsliIcon)

            GlobalData.habits.add(newHabit)

            Toast.makeText(context, "Habit Baru Ditambahkan!", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
    }
}