import org.gradle.api.artifacts.dsl.DependencyHandler

fun  DependencyHandler.commonModule(){
    retrofit()
    jetpackCompose()
}