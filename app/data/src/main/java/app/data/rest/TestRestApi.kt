package app.data.rest

import app.data.TestRemoteDataSource
import app.data.data.UserResponse
import retrofit2.http.GET

interface TestRestApi : TestRemoteDataSource {
    @GET("/users")
    override suspend fun getUsers(): Result<List<UserResponse>>
}