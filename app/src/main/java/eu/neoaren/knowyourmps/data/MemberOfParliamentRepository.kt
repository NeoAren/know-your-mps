package eu.neoaren.knowyourmps.data

import eu.neoaren.knowyourmps.api.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemberOfParliamentRepository @Inject constructor(
  private val memberOfParliamentDao: MemberOfParliamentDao,
  private val apiService: ApiService,
) {

  suspend fun loadDataFromApi() = apiService.getMPs()

  suspend fun insert(entry: MemberOfParliament) = memberOfParliamentDao.insert(entry)

  fun getParties() = memberOfParliamentDao.getParties()

  fun getByParty(party: String) = memberOfParliamentDao.getByParty(party)

  fun getByPersonNumber(personNumber: Int) = memberOfParliamentDao.getByPersonNumber(personNumber)

}
