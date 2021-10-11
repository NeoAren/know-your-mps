package eu.neoaren.knowyourmps.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import eu.neoaren.knowyourmps.workers.SeedDatabaseWorker

@Database(entities = [MemberOfParliament::class, Note::class], version = 5)
abstract class AppDatabase : RoomDatabase() {

  abstract fun memberOfParliamentDao(): MemberOfParliamentDao

  abstract fun noteDao(): NoteDao

  companion object {

    @Volatile
    private var instance: AppDatabase? = null

    // Get an instance of the database
    fun getInstance(context: Context): AppDatabase {
      return instance ?: synchronized(this) {
        Room.databaseBuilder(context, AppDatabase::class.java, "mps_db")
          .fallbackToDestructiveMigration()
          .addCallback(object : RoomDatabase.Callback() {
            // Create one time work request to seed the database
            override fun onCreate(db: SupportSQLiteDatabase) {
              super.onCreate(db)
              val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
                .setConstraints(SeedDatabaseWorker.constraints)
                .build()
              WorkManager.getInstance(context).enqueue(request)
            }
          })
          .build()
          .also { instance = it }
      }
    }

  }

}
