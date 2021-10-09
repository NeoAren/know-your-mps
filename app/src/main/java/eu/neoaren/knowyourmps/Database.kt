package eu.neoaren.knowyourmps

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemberOfParliamentDAO {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(entry: MemberOfParliament)
}

@Database(entities = [MemberOfParliament::class], version = 3)
abstract class MPsDatabase : RoomDatabase() {

  abstract val memberOfParliamentDAO: MemberOfParliamentDAO

  companion object {

    @Volatile
    private var INSTANCE: MPsDatabase? = null

    fun getInstance(context: Context): MPsDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(context, MPsDatabase::class.java, "mps_db")
          .fallbackToDestructiveMigration()
          .build()
        INSTANCE = instance
        return instance
      }
    }

  }

}
