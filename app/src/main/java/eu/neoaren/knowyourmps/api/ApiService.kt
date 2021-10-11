package eu.neoaren.knowyourmps.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import eu.neoaren.knowyourmps.data.MemberOfParliament
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface ApiService {

  @GET("/~peterh/mps.json")
  suspend fun getMPs(): List<MemberOfParliament>

  companion object {

    private const val BASE_URL = "https://users.metropolia.fi/"

    // Create retrofit service with moshi converter
    fun create(): ApiService {
      val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

      return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(ApiService::class.java)
    }

  }

}
