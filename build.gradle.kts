
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

val exposed_version: String by project
val h2_version: String by project
plugins {
    kotlin("jvm") version "1.9.22"
    id("io.ktor.plugin") version "2.3.7"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    //password hash
    implementation("org.mindrot:jbcrypt:0.4")

    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    implementation("com.microsoft.sqlserver:mssql-jdbc:9.4.0.jre11")
    implementation ("mysql:mysql-connector-java:8.0.28")
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("com.h2database:h2:$h2_version")

    implementation(kotlin("stdlib"))
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1")


    implementation ("io.ktor:ktor-websockets")
    implementation("io.ktor:ktor-server-websockets")
    implementation("io.ktor:ktor-network") //for sockets
    implementation("io.ktor:ktor-server-core")

    implementation("io.ktor:ktor-client-core")
    implementation("io.ktor:ktor-client-cio")
    implementation("io.ktor:ktor-client-logging")
    implementation("io.ktor:ktor-client-json:2.3.3")

    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.2.4")
    implementation("io.ktor:ktor-server-netty:2.2.4")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:2.2.4")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

