package com.alfons.meonghabittracker.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        //tes
        binding.btnCreateHabit.setOnClickListener {
            val name = binding.txtHabitName.text.toString()
            val desc = binding.txtDescription.text.toString()
            val goal = binding.txtGoal.text.toString().toIntOrNull() ?: 0
            val unit = binding.txtUnit.text.toString()

            val newHabit = Habit("99", name, desc, goal, 0, unit, "ic_default")

            GlobalData.habits.add(newHabit)

            Toast.makeText(context, "Habit Baru Ditambahkan!", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
    }
}