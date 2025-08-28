package app.data

import app.data.rest.RestConfig
import app.data.rest.TestClient
import app.data.rest.TestRestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRestConfig(): RestConfig = RestConfig(
        writeTimeoutInSeconds = 300,
        readTimeoutInSeconds = 300,
        baseUrl = "https://jsonplaceholder.typicode.com/"
    )

    @Provides
    @Singleton
    fun provideImdbImdbRestRemote(restConfig: RestConfig): TestRemoteDataSource = TestClient(restConfig).create(
        TestRestApi::class.java
    )



}