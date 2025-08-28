import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

fun DependencyHandler.dataModuleDependencies(){
    implementation(project = project(":app:common"))
    retrofit()
    room();
    daggerHilt()
    roboElectric()
}