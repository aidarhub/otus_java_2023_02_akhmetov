import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel
import org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES

plugins {
    idea
    id("fr.brouillard.oss.gradle.jgitver")
    id("io.spring.dependency-management")
    id("org.springframework.boot") apply false
}

idea {
    project {
        languageLevel = IdeaLanguageLevel(17)
    }
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

allprojects {
    group = "org.example"

    repositories {
        mavenLocal()
        mavenCentral()
    }

    val guava: String by project
    val testContainersBom: String by project
    val protobufBom: String by project
    val jmh: String by project
    val ehcache: String by project
    val lombok: String by project
    val gson: String by project

    val jetty: String by project
    val freemarker: String by project

    val reflections: String by project

    apply(plugin = "io.spring.dependency-management")
    dependencyManagement {
        dependencies {
            imports {
                mavenBom(BOM_COORDINATES)
                mavenBom("org.testcontainers:testcontainers-bom:$testContainersBom")
                mavenBom("com.google.protobuf:protobuf-bom:$protobufBom")
            }
            dependency("com.google.guava:guava:$guava")
            dependency("org.openjdk.jmh:jmh-core:$jmh")
            dependency("org.openjdk.jmh:jmh-generator-annprocess:$jmh")
            dependency("org.ehcache:ehcache:$ehcache")
            dependency("org.projectlombok:lombok:$lombok")
            dependency("com.google.code.gson:gson:$gson")

            dependency("org.eclipse.jetty:jetty-servlet:$jetty")
            dependency("org.eclipse.jetty:jetty-server:$jetty")
            dependency("org.eclipse.jetty:jetty-webapp:$jetty")
            dependency("org.eclipse.jetty:jetty-security:$jetty")
            dependency("org.eclipse.jetty:jetty-http:$jetty")
            dependency("org.eclipse.jetty:jetty-io:$jetty")
            dependency("org.eclipse.jetty:jetty-util:$jetty")
            dependency("org.freemarker:freemarker:$freemarker")
            dependency("org.reflections:reflections:$reflections")
        }
    }
}

subprojects {
    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging.showExceptions = true
        reports {
            junitXml.required.set(true)
            html.required.set(true)
        }
    }
}
