import org.gradle.api.artifacts.dsl.DependencyHandler

fun  DependencyHandler.room(){
    val version = "2.7.2"
    implementation("androidx.room:room-ktx:$version")
    implementation("androidx.room:room-runtime:$version")
    ksp("androidx.room:room-compiler:$version")
    testImplementation("androidx.room:room-testing:$version")
}