import org.gradle.api.artifacts.dsl.DependencyHandler
import  testImplementation

fun  DependencyHandler.roboElectric(){
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.robolectric:robolectric:4.13")
}