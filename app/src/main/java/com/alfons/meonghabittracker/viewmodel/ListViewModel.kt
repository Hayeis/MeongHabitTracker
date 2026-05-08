package com.alfons.meonghabittracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alfons.meonghabittracker.model.Habit

class ListViewModel: ViewModel() {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    val habitLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun refresh() {
        loadingLD.value = true
        habitLoadErrorLD.value = false
        habitsLD.value = arrayListOf(
            Habit("1", "Minum Air", "Minum 8 gelas sehari", 8, "Gelas", "ic_water"),
            Habit("2", "Olahraga", "Push up & Dumbbell", 30, "Menit", "ic_fitness"),
            Habit("3", "Membaca", "Baca buku sebelum tidur", 1, "Bab", "ic_book")
        )

        habitLoadErrorLD.value = false
        loadingLD.value = false
    }
}