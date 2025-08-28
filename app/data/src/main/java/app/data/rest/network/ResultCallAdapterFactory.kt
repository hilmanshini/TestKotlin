package app.common.network

import kotlinx.coroutines.Deferred
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (returnType !is ParameterizedType) return null

        val rawType = getRawType(returnType)
        val isDeferred = rawType == Deferred::class.java

//        if (rawType != Result::class.java) return null

        val successType = getParameterUpperBound(0, returnType)
        val dataType = getParameterUpperBound(0, successType as ParameterizedType)
        return ResultCallAdapter<Any>(dataType)
    }
}
class ResultCallAdapter<T>(
    private val responseType: Type
) : CallAdapter<T, Call<Result<T>>> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<T>): Call<Result<T>> {
        // Assign the outer object to a variable
        val outerCall = object : Call<Result<T>> {
            val outerCall = this;
            override fun enqueue(callback: Callback<Result<T>>) {
                kotlin.runCatching {
                    call.enqueue(object : Callback<T> {
                        override fun onResponse(innerCall: Call<T>, response: Response<T>) {
                            val result = if (response.isSuccessful && response.body() != null) {
                                Result.success(response.body()!!)
                            } else {
                                Result.failure(Throwable("HTTP ${response.code()}: ${response.message()}"))
                            }
                            // Use outerCall as the first argument
                            callback.onResponse(outerCall, Response.success(result))
                        }

                        override fun onFailure(innerCall: Call<T>, t: Throwable) {
                            callback.onResponse(outerCall, Response.success(Result.failure(t)))
                        }
                    })
                }.onFailure {
                    callback.onResponse(outerCall, Response.success(Result.failure(it)))
                }

            }

            override fun execute(): Response<Result<T>> {
                return try {
                    val response = call.execute()
                    val result = if (response.isSuccessful && response.body() != null) {
                        Result.success(response.body()!!)
                    } else {
                        Result.failure(Throwable("HTTP ${response.code()}: ${response.message()}"))
                    }
                    Response.success(result)
                } catch (e: Exception) {
                    Response.success(Result.failure(e))
                }
            }

            override fun isExecuted() = call.isExecuted
            override fun cancel() = call.cancel()
            override fun isCanceled() = call.isCanceled
            override fun clone(): Call<Result<T>> {
                println()
                return adapt(call.clone());
            }
            override fun request(): Request = call.request()
            override fun timeout(): Timeout = call.timeout()
        }
        return outerCall
    }
}