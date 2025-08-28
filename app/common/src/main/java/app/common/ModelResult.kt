package app.common

import kotlin.getOrThrow
import kotlin.let


sealed class ModelResult<T> {

    class Success<T>(val data: T) : ModelResult<T>()
    class Failure<T>(val exception: Throwable) : ModelResult<T>()
    class Loading<T>() : ModelResult<T>()
    class Idle<T>() : ModelResult<T>()

    fun isSuccess() = this is Success
    fun isLoading() = this is Loading
    fun isFailure() = this is Failure
    fun  asSuccess() = this as Success<T>
    inline fun  onSuccess(call:(T)->Unit):ModelResult<T>{
        if(this is Success<T>){
            call(this.data)
        }
        return  this;
    }
    inline fun  onFailure(call:(Throwable)->Unit):ModelResult<T>{
        if(this is Failure<T>){
            call(this.exception)
        }
        return  this;
    }

    inline fun  onNotLoading(call:(ModelResult<T>)->Unit):ModelResult<T>{
        if(this !is Loading<T>){
            call(this)
        }
        return  this;
    }

    inline fun  onLoading(call:()->Unit):ModelResult<T>{
        if(this is Loading<T>){
            call()
        }
        return  this;
    }
}

fun <T> Result<T>.asModelResult(): ModelResult<T> {
    if (this.isSuccess) {
        return try {
            ModelResult.Success(this.getOrThrow())
        } catch (e: Exception) {
            ModelResult.Failure(e)
        }
    } else {
        val exception: Throwable? = this.exceptionOrNull()
        return  exception?.let {
            ModelResult.Failure(it)
        } ?: ModelResult.Failure(kotlin.Exception("Unknown Error"))
    }
}
