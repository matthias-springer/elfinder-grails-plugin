package org.elfinder;

import java.io.File;

import org.elfinder.servlets.config.AbstractConnectorConfig;
import org.elfinder.servlets.fs.DiskFsImpl;
import org.elfinder.servlets.fs.IFsImpl;

/**
 * @author Antoine Walter (www.anw.fr), Matthias Springer
 * @date 29 aug. 2011
 * @version $Id$
 * @license BSD
 */

public class ElfinderConfig extends AbstractConnectorConfig {

	/**
	 * Filesystem.
	 */
	private DiskFsImpl fsImpl;

	public ElfinderConfig() {
		fsImpl = new DiskFsImpl();
	}

	@Override
	public IFsImpl getFs() {
		return fsImpl;
	}

	@Override
	public String getRoot() {
		return ElfinderServlet.HOME_SHARED_DOCS;
	}

	@Override
	public String getRootUrl() {
		return ElfinderServlet.REALOBJECTURL;
	}
	
	@Override
	public String getFileUrl(File path) {
		return getRootUrl() + "?p=" + getRelativePath(path);
	}

	@Override
	public String rootAliasOrBaseName() {
		return ElfinderServlet.SHARED_DOCS;
	}
	
	@Override
	public String getThumbnailUrl(File path) {
		return ElfinderServlet.THUMBNAIL + getRelativePath(path);
	}
	
	@Override
	public boolean hasThumbnail(File path) {
		String mimeType = getMime(path);
		return mimeType.contains("image");
	}
}
