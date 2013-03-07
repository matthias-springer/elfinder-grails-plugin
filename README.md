elfinder-grails-plugin
======================

Installation
------------
* Install the plugin
* Use tag ```<g:elfinder />``` include elfinder.
* In your ```Config.groovy```, specify the home directory, i.e. the directory accessible with elfinder: 
  ```elfinder.homeSharedDocs = "/tmp/shared"```

Integrate with CKEditor
-----------------------
Use ```ckeditor``` attribute: ```<g:elfinder ckeditor="1" />```

Authentication / Authorization
------------------------------
In your ```Config.groovy```, set ```elfinder.authentication```. It must implement the ```org.elfinder.ServletAuthentication``` interface. You have to implement the method ```public boolean isAllowed(HttpServletRequest request)``` that returns true iff access should be granted. 

*Important:* read access via ```virtualproxy``` servlet is always granted. ```elfinder.authentication``` applies only to write, delete, ...

Here is an example how to integrate Spring Security.
```groovy
// Integration of Spring Security Core in elfinder servlets
elfinder.authentication = new org.elfinder.ServletAuthentication() {

  private final static String SECURITY_ROLE = 'ROLE_ADMIN'

  @Override
  public boolean isAllowed(HttpServletRequest request) {
    def ctx = SCH.servletContext.getAttribute(GA.APPLICATION_CONTEXT)
    def sessionId =  request.cookies.find({cookie -> cookie.name == "JSESSIONID"}).value

    try {
      return ctx.sessionRegistry.getSessionInformation(sessionId).getPrincipal().getAuthorities().find({auth -> auth.getAuthority() == SECURITY_ROLE}) != null
    }
    catch (Exception e)
    {
      // invalid session cookie provided
      return false
    }
  }
}
```


