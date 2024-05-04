plugins {
    application
    idea
}

group = "au.com.rainmore"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

application {
    mainClass.set(listOf(project.group.toString(), "Main").joinToString("."))
}


tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.run.configure {
    standardInput = System.`in`
}

tasks.test {
    useJUnitPlatform()
}
