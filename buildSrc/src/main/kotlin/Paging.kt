import org.gradle.api.artifacts.dsl.DependencyHandler

fun  DependencyHandler.paging(){
    implementation("androidx.paging:paging-runtime:3.3.6")
    implementation("androidx.paging:paging-compose:3.3.6")
}