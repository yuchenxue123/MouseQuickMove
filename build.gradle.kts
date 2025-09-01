import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo

plugins {
    kotlin("jvm")
    id("fml-loom")
}

val mod_name: String by project
val mod_version: String by project

version = mod_version

base {
    archivesName = mod_name
}

repositories {
    maven {
        name = "Jitpack"
        url = uri("https://jitpack.io")
    }
}

val access_widener: String by project

loom {
    accessWidenerPath = file("src/main/resources/$access_widener")
    mergedMinecraftJar()
    fml = File("libs/loader-v3.4.2.jar")

    mods {
        create(mod_name) {
            sourceSet(sourceSets.main.get())
        }
    }
}

val packageImplementation = configurations.register("packageImplementation") {
    configurations.implementation.get().extendsFrom(this)
}

fun DependencyHandler.packageImplementation(dependencyNotation: Any) {
    add("packageImplementation", dependencyNotation)
}

fun DependencyHandler.packageImplementation(dependencyNotation: Any, dependencyConfiguration: Action<ExternalModuleDependency>) {
    addDependencyTo(this,"packageImplementation", dependencyNotation, dependencyConfiguration)
}

val minecraft_version: String by project
val kotlin_version: String by project

dependencies {
    minecraft("com.mojang:minecraft:$minecraft_version")
    mappings(loom.fmlMCPMappings())
    implementation(files(loom.fml.toPath()))

    packageImplementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")

    packageImplementation("com.github.yuchenxue123:event-system-kotlin:1.0") {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib")
    }
}

val mod_id: String by project
val mod_description: String by project

val properties = mapOf(
    "id" to mod_id,
    "name" to mod_name,
    "version" to mod_version,
    "description" to mod_description,
    "widener" to access_widener
)

tasks.processResources {
    inputs.property("version", mod_version)

    filesMatching("fml.mod.json") {
        expand(properties)
    }
}

tasks.jar {
    from({
        packageImplementation.get().map {
            if (it.isDirectory) it else zipTree(it)
        }
    }) {
        exclude("META-INF/")
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(17)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmToolchain(17)
    }
}

