package app.datasource

import app.data.TestRemoteDataSource
import app.domain.model.UserModel

class UserDataSource(
    val testRemoteDataSource: TestRemoteDataSource
) {

    suspend fun getUserData(): Result<List<UserModel>> {
        return testRemoteDataSource.getUsers().map {
            it.map {
                UserModel(
                    it.name, it.email
                )
            }
        }
    }
}