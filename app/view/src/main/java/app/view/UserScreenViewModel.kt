package app.view

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.common.ModelResult
import app.domain.model.UserModel
import app.usecase.GetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserScreenViewModel @Inject constructor(
    val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {

    val userData = mutableStateOf<ModelResult<List<UserModel>>>(ModelResult.Loading())

    init {
        loadGenre()
    }


    fun loadGenre() {
        viewModelScope.launch {
            getUserDataUseCase().collect {
                userData.value = it
            }
        }
    }
}