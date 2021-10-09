package eu.neoaren.knowyourmps.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.neoaren.knowyourmps.api.ApiService
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

  @Singleton
  @Provides
  fun provideApiService(): ApiService {
    return ApiService.create()
  }

}
