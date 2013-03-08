elfinder-grails-plugin
======================
This Grails plugin integrates elFinder (https://github.com/Studio-42/elFinder) by wrapping the existing elFinder servlets (https://github.com/Studio-42/elfinder-servlet).

Installation
------------
* Install the plugin
* Use tag ```<g:elfinder />``` to include elfinder.
* In your ```Config.groovy```, specify the home directory, i.e. the directory accessible with elfinder:
  ```elfinder.homeSharedDocs = "/tmp/shared"```

Integrate with CKEditor
-----------------------
Use ```ckeditor``` attribute (e.g. ```<g:elfinder ckeditor="1" />```) and follow these instructions to configure CKEditor: https://github.com/Studio-42/elFinder/wiki/Integration-with-CKEditor

Authentication / Authorization
------------------------------
In your ```Config.groovy```, set ```elfinder.authentication```. It must implement the ```org.elfinder.ServletAuthentication``` interface. You have to implement the method ```public boolean isAllowed(HttpServletRequest request)``` that returns true iff access should be granted. 

*Important:* read access via ```virtualproxy``` servlet is always granted. ```elfinder.authentication``` applies only to write, delete, ...

Here is an example how to integrate Spring Security.
```groovy
// Integration of Spring Security Core in elfinder servlets
import org.springframework.security.core.context.SecurityContextHolder

elfinder.authentication = new org.elfinder.ServletAuthentication() {

  private final static String SECURITY_ROLE = 'ROLE_ADMIN'

  @Override
  public boolean isAllowed(HttpServletRequest request) {
    try {
      SecurityContextHolder.context.authentication.principal.authorities.find({auth -> auth.authority == SECURITY_ROLE})
    }
    catch (Exception e) {
      // invalid session cookie provided
      return false
    }
  }
}
```

What it looks like
------------------
![alt text](https://raw.github.com/matthias-springer/elfinder-grails-plugin/master/example.png "Example")
