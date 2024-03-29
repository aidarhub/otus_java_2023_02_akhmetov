rootProject.name = "otus_java_2023_02_akhmetov"
include("hw01-gradle")
include("hw02-generics")
include("hw03-annotations")
include("hw04-gc")
include("hw05-byteCodes")
include("hw06-solid")
include("hw07-pattern")
include("hw08-io")
include("hw09-jdbc")
include("hw09-jdbc:demo")
findProject(":hw09-jdbc:demo")?.name = "demo"
include("hw09-jdbc:homework")
findProject(":hw09-jdbc:homework")?.name = "homework"
include("hw10-jpql")
include("hw11-cache")
include("hw12-web")
include("hw13-depInj")

pluginManagement {
    val jgitver: String by settings
    val dependencyManagement: String by settings
    val springframeworkBoot: String by settings
    val johnrengelmanShadow: String by settings
    val jib: String by settings

    plugins {
        id("fr.brouillard.oss.gradle.jgitver") version jgitver
        id("io.spring.dependency-management") version dependencyManagement
        id("org.springframework.boot") version springframeworkBoot
        id("com.github.johnrengelman.shadow") version johnrengelmanShadow
        id("com.google.cloud.tools.jib") version jib
    }
}