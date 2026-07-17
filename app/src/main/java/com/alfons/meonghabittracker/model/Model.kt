package com.alfons.meonghabittracker.model
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Habit(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    @ColumnInfo(name="name")
    var name:String,
    @ColumnInfo(name="description")
    var description:String,
    @ColumnInfo(name="goal")
    var goal:Int,
    @ColumnInfo(name="currentProgress")
    var currentProgress: Int,
    @ColumnInfo(name="unit")
    var unit:String,
    @ColumnInfo(name="icon")
    var icon:String
): Parcelable

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name = "password") var password: String
)