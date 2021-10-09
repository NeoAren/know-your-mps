package eu.neoaren.knowyourmps.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import eu.neoaren.knowyourmps.data.AppDatabase
import eu.neoaren.knowyourmps.data.MemberOfParliamentDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

  @Singleton
  @Provides
  fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
    return AppDatabase.getInstance(context)
  }

  @Provides
  fun provideMemberOfParliamentDao(appDatabase: AppDatabase): MemberOfParliamentDao {
    return appDatabase.memberOfParliamentDao()
  }

}
