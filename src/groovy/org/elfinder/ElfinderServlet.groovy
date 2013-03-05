package org.elfinder;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.elfinder.servlets.AbstractConnectorServlet;
import org.elfinder.servlets.config.AbstractConnectorConfig;

import org.codehaus.groovy.grails.commons.ConfigurationHolder;

/**
 * @author Özkan Pakdil, Matthias Springer
 * @date 29 aug. 2011
 * @version $Id$
 * @license BSD
 */

@SuppressWarnings("serial")
public class ElfinderServlet extends AbstractConnectorServlet {

	public static String SHARED_DOCS = "Shared docs";
	public static String THUMBNAIL = "/elfinder/elfinder/thumbnailer?p=";
	public static String HOME_SHARED_DOCS = "/tmp/shared_docs";
	public static String REALOBJECTURL = "/elfinder/elfinder/virtualproxy";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
    
    def appConfig = ConfigurationHolder.config

    if (appConfig.elfinder?.homeSharedDocs) {
 	  	HOME_SHARED_DOCS = appConfig.elfinder.homeSharedDocs;
  	  File f=new File(HOME_SHARED_DOCS);
	  	if(!f.exists()){
 		  	f.mkdirs();
	  	}
    }
    else {
      log.warn "No elfinder home directory specified. Using default value ${HOME_SHARED_DOCS}."
 		}
	  
    if (appConfig.elfinder?.thumbnail)
		  THUMBNAIL = appConfig.elfinder.thumbnail;

    if (appConfig.elfinder?.sharedDocs)
	  	SHARED_DOCS = appConfig.elfinder.sharedDocs;

  	if (appConfig.elfinder?.realObjectURL)
  	 	REALOBJECTURL = appConfig.elfinder.realObjectURL;
  }

	@Override
	protected AbstractConnectorConfig prepareConfig(HttpServletRequest request) {
		return new ElfinderConfig();
	}

}