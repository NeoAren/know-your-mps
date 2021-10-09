package eu.neoaren.knowyourmps.data

import android.content.Context
import androidx.room.*

@Database(entities = [MemberOfParliament::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

  abstract fun memberOfParliamentDao(): MemberOfParliamentDao

  companion object {

    @Volatile
    private var instance: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
      return instance ?: synchronized(this) {
        Room.databaseBuilder(context, AppDatabase::class.java, "mps_db")
          .fallbackToDestructiveMigration()
          .build()
          .also { instance = it }
      }
    }

  }

}
