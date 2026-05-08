package com.alfons.meonghabittracker.view

import android.view.LayoutInflater
import android.view.ViewGroup
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
        holder.binding.txtJudulHabit.text = habitList[position].name
        holder.binding.txtNote.text = habitList[position].description
        holder.binding.txtTotalProgress.text = "${habitList[position].goal} ${habitList[position].unit}"

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