package app.domain

import app.usecase.GetUserDataUseCase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class UsecaseTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }


    @Inject
    lateinit var getUserDataUseCase: GetUserDataUseCase

    fun <T> callUsecase(call:suspend (Continuation<T>)->Unit): T {
        return runBlocking {
            withContext(Dispatchers.IO) {
                val z = suspendCoroutine { cont ->
                    runBlocking {
                        withContext(Dispatchers.IO) {
                            call(cont)
                        }
                    }
                }
                return@withContext z
            }
        }
    }


    @Test
    fun testUsecase() = runTest {

        val genre = callUsecase { cont ->
            getUserDataUseCase().collect {
                it.onNotLoading {
                    cont.resume(it)
                }
            }
        }
        println(genre)

    }
}