import java.security.MessageDigest

plugins {
    kotlin("jvm") version "2.4.10"
    kotlin("plugin.serialization") version "2.4.10"
    application
}

group = "org.on1ks.remanga"
version = "0.2.2"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.11.0")
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass = "org.on1ks.remanga.api.MainKt"
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    archiveFileName = "remanga-api-gen.jar"
    manifest.attributes["Main-Class"] = application.mainClass.get()
}

val packageInput = layout.buildDirectory.dir("jpackage/input")
val appImageDirectory = layout.buildDirectory.dir("jpackage/image")

val preparePackage by tasks.registering(Sync::class) {
    dependsOn(tasks.jar)
    into(packageInput)
    from(tasks.jar)
    from(configurations.runtimeClasspath)
}

val portableImage by tasks.registering(Exec::class) {
    dependsOn(preparePackage)
    doFirst {
        delete(appImageDirectory)
        appImageDirectory.get().asFile.mkdirs()
    }
    commandLine(
        "jpackage",
        "--type", "app-image",
        "--name", "remanga_api_gen",
        "--input", packageInput.get().asFile.absolutePath,
        "--main-jar", "remanga-api-gen.jar",
        "--main-class", application.mainClass.get(),
        "--dest", appImageDirectory.get().asFile.absolutePath,
        "--app-version", project.version.toString(),
        "--icon", project.file("src/main/resources/icons/remanga-official.ico").absolutePath,
        "--description", "Re:Manga API Documentation Generator",
        "--vendor", "Ps1xo-On1kS",
        "--add-launcher", "remanga_api_gen_cli=${project.file("src/jpackage/cli.properties").absolutePath}",
    )
}

val portableZip by tasks.registering(Zip::class) {
    dependsOn(portableImage)
    archiveFileName = "remanga-api-gen-${project.version}-windows-x64.zip"
    destinationDirectory = layout.buildDirectory.dir("distributions")
    from(appImageDirectory)
}

tasks.register("portableSha256") {
    dependsOn(portableZip)
    val archive = portableZip.flatMap { it.archiveFile }
    val checksum = layout.buildDirectory.file("distributions/${portableZip.get().archiveFileName.get()}.sha256")
    inputs.file(archive)
    outputs.file(checksum)
    doLast {
        val file = archive.get().asFile
        val digest = MessageDigest.getInstance("SHA-256").digest(file.readBytes())
        checksum.get().asFile.writeText("${digest.joinToString("") { "%02x".format(it) }}  ${file.name}\n")
    }
}
