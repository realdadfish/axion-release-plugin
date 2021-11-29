package pl.allegro.tech.build.axion.release.domain

import org.gradle.api.Project
import org.gradle.api.file.ProjectLayout
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.model.ObjectFactory
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile

import javax.inject.Inject

class PinConfig {
    @Input
    boolean enabled = false

    private RegularFileProperty pinFile

    @OutputFile
    RegularFileProperty getPinFile() {
        return pinFile
    }

    @Inject
    PinConfig(ObjectFactory objects, ProjectLayout layout) {
        pinFile = objects.fileProperty().convention(layout.projectDirectory.file("pinned-version.json"))
    }
}
