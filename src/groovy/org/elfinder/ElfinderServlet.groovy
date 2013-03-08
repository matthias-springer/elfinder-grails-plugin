package org.elfinder

import javax.servlet.ServletConfig
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest

import org.codehaus.groovy.grails.commons.ApplicationAttributes
import org.elfinder.servlets.AbstractConnectorServlet
import org.elfinder.servlets.config.AbstractConnectorConfig

/**
 * @author Özkan Pakdil, Matthias Springer
 * @date 29 aug. 2011
 * @license BSD
 */
class ElfinderServlet extends AbstractConnectorServlet {

	public static String SHARED_DOCS = "Shared docs"
	public static String HOME_SHARED_DOCS = "/tmp/shared_docs"

	public static ServletAuthentication AUTHENTICATION = new ServletAuthentication() {
		boolean isAllowed(HttpServletRequest request) {
			// default: no access control
			return true
		}
	}

	private static final long serialVersionUID = 1

	@Override
	void init(ServletConfig config) throws ServletException {
		super.init(config)

		def ctx = config.servletContext.getAttribute(ApplicationAttributes.APPLICATION_CONTEXT)
		def appConfig = ctx.grailsApplication.config

		if (appConfig.elfinder?.homeSharedDocs) {
			HOME_SHARED_DOCS = appConfig.elfinder.homeSharedDocs
			File f=new File(HOME_SHARED_DOCS)
			if(!f.exists()){
				f.mkdirs()
			}
		}
		else {
			log.warn "No elfinder home directory specified. Using default value ${HOME_SHARED_DOCS}."
		}

		if (appConfig.elfinder?.sharedDocs) {
			SHARED_DOCS = appConfig.elfinder.sharedDocs
		}

		if (appConfig.elfinder?.authentication) {
			AUTHENTICATION = appConfig.elfinder.authentication
		}
	}

	@Override
	protected AbstractConnectorConfig prepareConfig(HttpServletRequest request) {
		return new ElfinderConfig()
	}
}
