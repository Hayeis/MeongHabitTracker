package com.alfons.meonghabittracker.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,
    var username: String = "",
    var password: String = ""
)

@Entity(
foreignKeys = [
    ForeignKey(
        entity = User::class,
        parentColumns = ["userId"],
        childColumns = ["ownerId"],
        onDelete = ForeignKey.CASCADE
        )
    ]
)
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
    var icon:String,
)