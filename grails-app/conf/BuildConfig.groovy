grails.server.port.http = 8081

grails.project.work.dir = 'target'

grails.project.dependency.resolution = {

    inherits 'global'
    log 'warn'

    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        compile 'org.apache.commons:commons-lang3:3.0', {
            excludes 'easymock', 'junit'
        }
        compile 'commons-fileupload:commons-fileupload:1.2.1', {
            excludes 'commons-io', 'junit', 'portlet-api', 'servlet-api'
        }
        compile 'commons-io:commons-io:2.3', {
            excludes 'junit'
        }
        compile 'com.mortennobel:java-image-scaling:0.8.5', {
            excludes 'filters', 'junit'
        }
        compile 'eu.medsea.mimeutil:mime-util:2.1.3', {
            excludes 'junit', 'log4j', 'slf4j-api', 'slf4j-log4j12'
        }
        compile 'org.json:json:20090211'
    }

    plugins {
        build(":release:2.2.1", ":rest-client-builder:1.0.3") {
            export = false
        }

        build ':tomcat:2.1.1', {
            export = false
        }
    }
}
