package eu.neoaren.knowyourmps.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MemberOfParliamentDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(entry: MemberOfParliament)

  @Query("select distinct party from mps_table")
  fun getParties(): Flow<List<String>>

  @Transaction
  @Query("select * from mps_table where party = :party")
  fun getByParty(party: String): Flow<List<MemberOfParliamentWithNotes>>

  @Query("select * from mps_table where personNumber = :personNumber")
  fun getByPersonNumber(personNumber: Int): Flow<MemberOfParliament>

}
