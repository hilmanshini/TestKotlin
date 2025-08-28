package app.usecase

import app.common.ModelResult
import app.common.asModelResult
import app.datasource.UserDataSource
import app.domain.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetUserDataUseCase(
    val userDataSource: UserDataSource
){
    operator fun invoke() = flow {
        emit(ModelResult.Loading())
        val response = userDataSource.getUserData().asModelResult()
        emit(response)
    }.flowOn(Dispatchers.IO)
}