package com.alfons.meonghabittracker.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.alfons.meonghabittracker.databinding.HabitListItemBinding
import com.alfons.meonghabittracker.model.Habit
import androidx.navigation.Navigation

class HabitListAdapter (val habitList:ArrayList<Habit>)
    : RecyclerView.Adapter<HabitListAdapter.HabitViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HabitViewHolder {

        val binding = HabitListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return HabitViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: HabitViewHolder,
        position: Int
    ) {
        val habit = habitList[position]
        val goal = habit.goal ?: 1


        holder.binding.txtJudulHabit.text = habitList[position].name
        holder.binding.txtNote.text = habitList[position].description
        holder.binding.txtTotalProgress.text = "${habit.currentProgress} / $goal ${habit.unit}"
        holder.binding.progressBar.max = goal
        holder.binding.progressBar.progress = habit.currentProgress

        //Untuk menampilkan ICON
        val iconname = habitList[position].icon ?: "ic_default"
        val resID = holder.itemView.context.resources.getIdentifier(
            iconname, "drawable", holder.itemView.context.packageName
        )

        if (resID != 0){
            holder.binding.imgIcon.setImageResource(resID)
        }
        else{
            holder.binding.imgIcon.setImageResource(android.R.drawable.ic_menu_help)
        }

        holder.binding.btnPlus.setOnClickListener {
            if (habit.currentProgress < goal) {
                habit.currentProgress += 1

                notifyItemChanged(position)
            } else {
                Toast.makeText(holder.itemView.context, "Goal tercapai!", Toast.LENGTH_SHORT).show()
            }
        }

        holder.binding.btnMinus.setOnClickListener {
            if (habit.currentProgress != 0) {
                habit.currentProgress -= 1

                notifyItemChanged(position)
            } else {
                Toast.makeText(holder.itemView.context, "Progress tidak dapat dikurangi karena masih belum ada progress!", Toast.LENGTH_SHORT).show()
            }
        }

    }
    override fun getItemCount(): Int {
        return habitList.size
    }

    fun updateHabitList(newHabitList: ArrayList<Habit>) {
        habitList.clear()
        habitList.addAll(newHabitList)
        notifyDataSetChanged()
    }


    class HabitViewHolder(var binding: HabitListItemBinding): RecyclerView.ViewHolder(binding.root)
}