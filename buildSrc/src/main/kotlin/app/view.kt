package app

import api
import coil
import daggerHilt
import implementation
import jetpackCompose
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import paging
import roboElectric

fun  DependencyHandler.viewDependenciesModule(){

    jetpackCompose()
    api(project = project(":app:domain"))
    api(project = project(":app:common"))
    paging()
    coil()
    daggerHilt()
}