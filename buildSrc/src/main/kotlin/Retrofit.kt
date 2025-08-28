import org.gradle.api.artifacts.dsl.DependencyHandler

fun  DependencyHandler.retrofit(){
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
}