package org.elfinder;

import javax.servlet.http.HttpServletRequest;

public interface ServletAuthentication {
  
  public boolean isAllowed(HttpServletRequest request);

}
