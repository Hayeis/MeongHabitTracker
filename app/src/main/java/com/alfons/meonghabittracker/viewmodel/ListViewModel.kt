package com.alfons.meonghabittracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alfons.meonghabittracker.model.GlobalData
import com.alfons.meonghabittracker.model.Habit

class ListViewModel: ViewModel() {
    val habitsLD = MutableLiveData<ArrayList<Habit>>()
    val habitLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun refresh() {
        loadingLD.value = true
        habitLoadErrorLD.value = false
        habitsLD.value = GlobalData.habits

        habitLoadErrorLD.value = false
        loadingLD.value = false
    }
}