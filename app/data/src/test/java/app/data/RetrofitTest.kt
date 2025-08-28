package app.data

import app.data.rest.RestConfig
import app.data.rest.TestClient
import app.data.rest.TestRestApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RetrofitTest {
    @Test
    fun  testRestApi(){
        val config = RestConfig(
            writeTimeoutInSeconds = 300,
            readTimeoutInSeconds = 300,
            baseUrl = "https://jsonplaceholder.typicode.com/"
        )
        val client = TestClient(config).create(TestRestApi::class.java)
        runBlocking {
            val response = client.getUsers();
            assert(response.isSuccess)
        }
    }
}