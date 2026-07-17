package com.alfons.meonghabittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.alfons.meonghabittracker.model.Habit
import com.alfons.meonghabittracker.model.HabitDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListHabitViewModel(application: Application) :
    AndroidViewModel(application), CoroutineScope {

    val habitLD = MutableLiveData<List<Habit>>()
    val habitLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh() {
        loadingLD.value = true
        habitLoadErrorLD.value = false
        launch{
            val db = HabitDatabase.buildDatabase(
                getApplication()
            )

            habitLD.postValue(db.habitDao().selectAllHabit())
            loadingLD.postValue(false)
        }
    }

    fun updateHabit(habit: Habit) {
        launch {
            val db = HabitDatabase.getInstance(getApplication())
            db.habitDao().updateHabit(habit)
        }
    }

    fun clearTask(habit:Habit){
        launch{
            val db = HabitDatabase.buildDatabase(
                getApplication()
            )
            db.habitDao().deleteHabit(habit)
            habitLD.postValue(db.habitDao().selectAllHabit())
        }
    }
}