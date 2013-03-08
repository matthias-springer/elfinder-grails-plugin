class ElfinderGrailsPlugin {
    def version = "0.1"
    def grailsVersion = "2.0 > *"
    def pluginExcludes = [
        "grails-app/controllers/**",
        "grails-app/views/**",
        "src/templates/**"
    ]

    def title = "elFinder Grails Servlet Plugin"
    def author = "Matthias Springer"
    def authorEmail = "me@matthiasspringer.de"
    def description = 'Wraps the elFinder servlet (https://github.com/Studio-42/elfinder-servlet-demo). It provides one single instance of that servlet!'

    def doWithWebDescriptor = { xml ->
      //def grailsEnv = System.getProperty("grails.env")
      
      def filterElement = xml.'filter'
      filterElement[filterElement.size()-1] + {
        'filter' {
          'filter-name'("upload")
          'filter-class'("UploadFilter")
        }
      }

      def filterMappingElement = xml.'filter-mapping'
      filterMappingElement[filterMappingElement.size()-1] + {
        'filter-mapping' {
          'filter-name'("upload")
          'url-pattern'("/elfinder/connector")
        }
      }

      def servletElement = xml.'servlet'
      servletElement[servletElement.size()-1] + {
        'servlet' {
          'servlet-name'("connector")
          'servlet-class'("org.elfinder.ElfinderServlet")
        }
      }

      servletElement[servletElement.size()-1] + {
        'servlet' {
          'servlet-name'("thumbnailer")
          'servlet-class'("org.elfinder.Thumbnailer")
        }
      }

      servletElement[servletElement.size()-1] + {
        'servlet' {
          'servlet-name'("virtualproxy")
          'servlet-class'("org.elfinder.VirtualProxy")
        }
      }

      def mappingElement = xml.'servlet-mapping'
      mappingElement + {
        'servlet-mapping' {
          'servlet-name'("connector")
          'url-pattern'("/elfinder/connector")
        }
      }

      mappingElement + {
        'servlet-mapping' {
          'servlet-name'("thumbnailer")
          'url-pattern'("/elfinder/thumbnailer")
        }
      }

      mappingElement + {
        'servlet-mapping' {
          'servlet-name'("virtualproxy")
          'url-pattern'("/elfinder/virtualproxy/*")
        }
      }
      
    }
}
