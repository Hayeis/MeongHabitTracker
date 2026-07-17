package com.alfons.meonghabittracker.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database([Habit::class, User::class], version = 2)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var instance: HabitDatabase? = null
        private val LOCK = Any()
        private var shouldPrepopulate = false

        fun buildDatabase(context: Context): HabitDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                HabitDatabase::class.java,
                "newhabitdb"
            ).addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    CoroutineScope(Dispatchers.IO).launch {
                        val database = getInstance(context)

                        val userId = database.userDao().insertUser(
                            User(username = "student", password = "123")
                        )

                        database.habitDao().insertAll(
                            Habit(
                                name = "Minum Air",
                                description = "Tetap terhidrasi sepanjang hari",
                                goal = 8,
                                currentProgress = 0,
                                unit = "gelas",
                                icon = "baseline_water_drop_24"
                            ),
                            Habit(
                                name = "Olahraga",
                                description = "Rutinitas Olahraga Harian",
                                goal = 30,
                                currentProgress = 0,
                                unit = "menit",
                                icon = "baseline_directions_run_24"
                            ),
                            Habit(
                                name = "Membaca Buku",
                                description = "Memperkaya Pengetahuanmu",
                                goal = 20,
                                currentProgress = 0,
                                unit = "halaman",
                                icon = "baseline_menu_book_24"
                            )
                        )
                    }
                }
            }).build()

        public fun getInstance(context: Context) = instance ?: buildDatabase(context)

        operator fun invoke(context: Context): HabitDatabase {
            synchronized(LOCK) {
                if (instance == null) {
                    instance = buildDatabase(context)
                }
                if (shouldPrepopulate) {
                    shouldPrepopulate = false
                    CoroutineScope(Dispatchers.IO).launch {
                        val database = instance!!
                        val userId = database.userDao().insertUser(
                            User(username = "student", password = "123")
                        )
                        database.habitDao().insertAll(
                            Habit(
                                name = "Minum Air",
                                description = "Tetap terhidrasi sepanjang hari",
                                goal = 8,
                                currentProgress = 0,
                                unit = "gelas",
                                icon = "baseline_water_drop_24"
                            ),
                            Habit(
                                name = "Olahraga",
                                description = "Rutinitas Olahraga Harian",
                                goal = 30,
                                currentProgress = 0,
                                unit = "menit",
                                icon = "baseline_directions_run_24"
                            ),
                            Habit(
                                name = "Membaca Buku",
                                description = "Memperkaya Pengetahuanmu",
                                goal = 20,
                                currentProgress = 0,
                                unit = "halaman",
                                icon = "baseline_menu_book_24"
                            )
                        )
                    }
                }
                return instance!!
            }
        }
    }
}
