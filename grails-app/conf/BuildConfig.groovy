grails.server.port.http = 8081

grails.project.dependency.resolution = {
    inherits("global") {}

    repositories {
        grailsCentral()
        mavenCentral()
    }

    dependencies {
        compile 'org.apache.commons:commons-lang3:3.0'
        compile 'commons-fileupload:commons-fileupload:1.2.1'
        compile 'commons-io:commons-io:2.3'
        compile 'com.mortennobel:java-image-scaling:0.8.5'
        compile 'eu.medsea.mimeutil:mime-util:2.1.3'
        compile 'org.json:json:20090211'
    }

    plugins {
        build(":tomcat:$grailsVersion",
              ":release:2.0.3",
              ":rest-client-builder:1.0.2") {
            export = false
        }

        runtime ':tomcat:2.1.1'
    }
}
