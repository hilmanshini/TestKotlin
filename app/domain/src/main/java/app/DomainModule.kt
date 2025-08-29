package app

import app.data.TestRemoteDataSource
import app.datasource.UserDataSource
import app.usecase.GetUserDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun procvideUsersRepo(
        testRemoteDataSource: TestRemoteDataSource
    ) = UserDataSource(
        testRemoteDataSource
    )

}



@Module
@InstallIn(ViewModelComponent::class)
open class UseCaseModule  {
    @Provides
    fun provideUsersRepo(repo: UserDataSource) = GetUserDataUseCase(repo)

}

