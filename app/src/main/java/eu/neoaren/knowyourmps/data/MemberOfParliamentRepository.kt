package eu.neoaren.knowyourmps.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemberOfParliamentRepository @Inject constructor(
  private val memberOfParliamentDao: MemberOfParliamentDao
) {

  suspend fun insert(entry: MemberOfParliament) = memberOfParliamentDao.insert(entry)

  fun getParties() = memberOfParliamentDao.getParties()

}
