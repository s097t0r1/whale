package me.s097t0r1.build

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.logging.Logger

class VersionCatalogHelper(private val project: Project) {

    private val versionCatalog = project.extensions.getByType(VersionCatalogsExtension::class.java).named("libs")
    private val logger: Logger = project.logger

    fun version(libName: String): String {
        return versionCatalog.findVersion(libName).get().requiredVersion
    }

    fun dependency(libName: String, configurationName: String) {
        versionCatalog.findLibrary(libName).ifPresentOrElse(
            { library ->
                project.dependencies.add(configurationName, library)
            },
            { logger.warn("Library '$libName' not found in version catalog.") }
        )
    }
}

// Extension functions for different configurations
fun Project.implementationFromCatalog(libName: String) {
    VersionCatalogHelper(this).dependency(libName, "implementation")
}

fun Project.testImplementationFromCatalog(libName: String) {
    VersionCatalogHelper(this).dependency(libName, "testImplementation")
}

fun Project.compileOnlyFromCatalog(libName: String) {
    VersionCatalogHelper(this).dependency(libName, "compileOnly")
}

fun Project.testRuntimeOnlyFromCatalog(libName: String) {
    VersionCatalogHelper(this).dependency(libName, "testRuntimeOnly")
}

fun Project.versionFromCatalog(versionName: String): String =
    VersionCatalogHelper(this).version(versionName)
