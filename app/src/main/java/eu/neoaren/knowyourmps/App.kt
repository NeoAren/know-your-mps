package eu.neoaren.knowyourmps

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import eu.neoaren.knowyourmps.workers.SeedDatabaseWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {

  @Inject lateinit var workerFactory: HiltWorkerFactory

  // Configure WorkManager
  override fun getWorkManagerConfiguration(): Configuration {
    return Configuration.Builder()
      .setMinimumLoggingLevel(Log.DEBUG)
      .setWorkerFactory(workerFactory)
      .build()
  }

  // Create periodic work request to re-seed the database every hour
  override fun onCreate() {
    super.onCreate()
    val request = PeriodicWorkRequestBuilder<SeedDatabaseWorker>(1, TimeUnit.HOURS)
      .setConstraints(SeedDatabaseWorker.constraints)
      .build()
    WorkManager.getInstance(this).enqueue(request)
  }

}
