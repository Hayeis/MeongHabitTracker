package com.alfons.meonghabittracker.model

data class Habit(
    var id: String?,
    var name: String?,
    var description: String?,
    var goal: Int?,
    var currentProgress: Int = 0,
    var unit: String?,
    var icon: String?
)
