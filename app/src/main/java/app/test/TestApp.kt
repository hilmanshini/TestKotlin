package app.test

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class TestApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            println(throwable)
        }
    }
}
