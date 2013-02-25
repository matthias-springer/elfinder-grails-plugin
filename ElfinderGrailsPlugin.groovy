class ElfinderGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.1 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "elFinder Grails Servlet Plugin" // Headline display name of the plugin
    def author = "Matthias Springer"
    def authorEmail = "me@matthiasspringer.de"
    def description = '''\
This plugin wraps the elFinder servlet (https://github.com/Studio-42/elfinder-servlet-demo). It provides one single instance of that servlet!
'''

    // URL to the plugin's documentation
    //def documentation = "http://grails.org/plugin/elfinder"

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    def doWithWebDescriptor = { xml ->
      //def grailsEnv = System.getProperty("grails.env")

      def servletElement = xml.'servlet'
      servletElement[servletElement.size()-1] + {
        'servlet' {
          'servlet-name'("connector")
          'servlet-class'("my.demo.MyDemoServlet")
        }
      }

      servletElement[servletElement.size()-1] + {
        'servlet' {
          'servlet-name'("thumbnailer")
          'servlet-class'("my.demo.Thumbnailer")
        }
      }

      servletElement[servletElement.size()-1] + {
        'servlet' {
          'servlet-name'("virtualproxy")
          'servlet-class'("my.demo.VirtualProxy")
        }
      }

      def mappingElement = xml.'servlet-mapping'
      mappingElement + {
        'servlet-mapping' {
          'servlet-name'("connector")
          'url-pattern'("/servlet/connector")
        }
      }

      mappingElement + {
        'servlet-mapping' {
          'servlet-name'("thumbnailer")
          'url-pattern'("/servlet/thumbnailer")
        }
      }

      mappingElement + {
        'servlet-mapping' {
          'servlet-name'("virtualproxy")
          'url-pattern'("/servlet/virtualproxy")
        }
      }
      
    }


    def doWithSpring = {
    }

    def doWithDynamicMethods = { ctx ->
    }

    def doWithApplicationContext = { applicationContext ->
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }

    def onShutdown = { event ->
    }
}
