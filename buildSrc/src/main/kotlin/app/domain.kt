package app

import api
import daggerHilt
import implementation
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import paging
import roboElectric

fun DependencyHandler.domainDependencies(){
    api(project = project(":app:data"))
    implementation(project = project(":app:common"))
    roboElectric()
    daggerHilt()// https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-test
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
    paging()
}