package pl.allegro.tech.build.axion.release

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import pl.allegro.tech.build.axion.release.domain.VersionConfig
import pl.allegro.tech.build.axion.release.domain.VersionService
import pl.allegro.tech.build.axion.release.domain.properties.Properties
import pl.allegro.tech.build.axion.release.infrastructure.di.Context
import pl.allegro.tech.build.axion.release.infrastructure.di.GradleAwareContext

class PinVersionTask extends DefaultTask {

    @Input
    @Optional
    VersionConfig versionConfig

    @TaskAction
    void pin() {
        VersionConfig config = GradleAwareContext.configOrCreateFromProject(project, versionConfig)
        Context context = GradleAwareContext.create(project, config)
        VersionService versionService = context.versionService()
        Properties rules = context.rules()
        VersionService.DecoratedVersion version = versionService.pinCurrentVersion(rules.version, rules.tag, rules.nextVersion, rules.pinning)
        logger.lifecycle("Version pinned to: ${version.decoratedVersion}")
    }
}
