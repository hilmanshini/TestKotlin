import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(name:String){
    add("implementation",name);
}

fun DependencyHandler.implementation(project:ProjectDependency){
    add("implementation",project);
}

fun DependencyHandler.api(project:ProjectDependency){
    add("api",project);
}

fun DependencyHandler.implementation(project:Dependency){
    add("api",project);
}

fun DependencyHandler.testImplementation(name:String){
    add("testImplementation",name);
}



fun DependencyHandler.kapt(name:String){
    add("kapt",name);
}

fun DependencyHandler.ksp(name:String){
    add("ksp",name);
}

fun DependencyHandler.kspTest(name:String){
    add("kspTest",name);
}

fun  DependencyHandler.androidTestImplementation(name:String){
    add("androidTestImplementation",name);
}

fun  DependencyHandler.debugImplementation(name:String){
    add("debugImplementation",name);
}
//fun DependencyHandler.implementation(name:String){
//    add("implementation",name);
//}

