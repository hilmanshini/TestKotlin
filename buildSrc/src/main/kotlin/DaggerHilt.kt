import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.daggerHilt(){
    implementation("com.google.dagger:hilt-android:2.56.2")
    ksp("com.google.dagger:hilt-android-compiler:2.56.2")
    testImplementation("com.google.dagger:hilt-android-testing:2.56.2")
    kspTest("com.google.dagger:hilt-android-compiler:2.56.2")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.56.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
}
