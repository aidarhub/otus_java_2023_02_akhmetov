plugins {
    id("java")
}

group = "org.example"

val junit_jupiter: String by project

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit_jupiter")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junit_jupiter")
}
