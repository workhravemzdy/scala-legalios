/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Scala library project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.0.2/userguide/building_java_projects.html
 */

plugins {
    // Apply the scala Plugin to add support for Scala.
    scala

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    `maven-publish`
    signing
}

group = "org.hravemzdy.legalios"
version = "0.22.2"

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use Scala 2.13 in our library project
    implementation("org.scala-lang:scala-library:2.13.3")
    implementation("org.scala-lang:scala-reflect:2.13.3")

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation("com.google.guava:guava:30.0-jre")

    // Use Scalatest for testing our library
    testImplementation("junit:junit:4.13.1")
    testImplementation("org.scalatest:scalatest_2.13:3.2.3")
    testImplementation("org.scalatestplus:junit-4-13_2.13:3.2.2.0")

    // Need scala-xml at test runtime
    testRuntimeOnly("org.scala-lang.modules:scala-xml_2.13:1.2.0")

    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api("org.apache.commons:commons-math3:3.6.1")
    // Reflections library https://github.com/ronmamo/reflections
    api("org.reflections:reflections:0.9.12")
}

tasks.scaladoc {
    source = sourceSets.main.get().allSource
}

val sourcesJar by tasks.registering(Jar::class) {
    classifier = "sources"
    from(sourceSets.main.get().allSource)
}

val javadocJar by tasks.registering(Jar::class) {
    classifier = "javadoc"
    from(tasks.scaladoc.get().destinationDir)
    dependsOn(tasks.scaladoc)
}

tasks.jar {
    archiveBaseName.set("scala-legalios")

    manifest {
        attributes(mapOf("Implementation-Title" to rootProject.name,
            "Implementation-Version" to project.version))
    }
}

publishing {
    publications {
        create<MavenPublication>("maven-scala") {
            artifactId = "scala-legalios"

            from(components["java"])
            artifact(sourcesJar)
            artifact(javadocJar)
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set("scala-legalios")
                description.set("payroll-procezor Salary, Health, Social, Taxing Properties for years 2011-2022")
                url.set("https://mzdyhrave.github.io/payrolldocs/")
                properties.set(mapOf(
                    "legalios.year.min" to "2011",
                    "legalios.year.max" to "2022",
                    "legalios.country" to "CZ-cz"
                ))
                licenses {
                    license {
                        name.set("The Unlicense")
                        url.set("https://unlicense.org")
                    }
                }
                developers {
                    developer {
                        id.set("ladislavlisy")
                        name.set("Ladislav Lisy")
                        email.set("info@hravemzdy.org")
                    }
                }
                scm {
                    connection.set("scm:git:git@github.com:mzdyhrave/legaliosscala.git")
                    developerConnection.set("scm:git:git@github.com:mzdyhrave/legaliosscala.git")
                    url.set("https://mzdyhrave.github.io/payrolldocs/")
                }
            }
        }
    }
    repositories {
        maven {
            // change URLs to point to your repos, e.g. http://my.org/repo
            //val releasesRepoUrl = uri(layout.buildDirectory.dir("repos/releases"))
            val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            //val snapshotsRepoUrl = uri(layout.buildDirectory.dir("repos/snapshots"))
            val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            authentication {
                credentials {
                    username = findProperty("ossrhUsername") as String?
                    password = findProperty("ossrhPassword") as String?
                }
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["maven-scala"])
}
