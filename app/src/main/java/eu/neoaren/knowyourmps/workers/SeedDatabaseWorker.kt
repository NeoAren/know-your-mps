package eu.neoaren.knowyourmps.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import eu.neoaren.knowyourmps.data.MemberOfParliamentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

@HiltWorker
class SeedDatabaseWorker @AssistedInject constructor(
  @Assisted context: Context,
  @Assisted workerParameters: WorkerParameters,
  private val memberOfParliamentRepository: MemberOfParliamentRepository,
) : CoroutineWorker(context, workerParameters) {
  override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
    Log.d(TAG, "Initiating database seeding process")
    try {
      val mps = memberOfParliamentRepository.loadDataFromApi()
      mps.forEach { memberOfParliamentRepository.insert(it) }
      Log.d(TAG, "Database seeding successful")
      Result.success()
    } catch (ex: Exception) {
      Log.e(TAG, "Error seeding database", ex)
      Result.failure()
    }
  }

  companion object {
    private const val TAG = "SeedDatabaseWorker"
    val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.UNMETERED).build()
  }
}
