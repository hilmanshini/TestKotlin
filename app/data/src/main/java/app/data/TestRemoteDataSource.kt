package app.data

import app.data.data.UserResponse


interface TestRemoteDataSource {

    suspend fun getUsers(): Result<List<UserResponse>>
}