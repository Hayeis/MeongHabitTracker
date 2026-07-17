package com.alfons.meonghabittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.alfons.meonghabittracker.model.Habit
import com.alfons.meonghabittracker.model.HabitDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailHabitViewModel (application: Application):
    AndroidViewModel(application), CoroutineScope{
    private val job = Job()

    fun addHabit(list:List<Habit>){
        launch {
            val db = HabitDatabase.buildDatabase(
                getApplication()
            )
            db.habitDao().insertAll(*list.toTypedArray())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}