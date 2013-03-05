import javax.servlet.*

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory

import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadFilter implements Filter {

    private Log log = LogFactory.getLog(getClass())

    public void init(FilterConfig filterConfig) throws ServletException { /* Do nothing */ }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {
        try {
            if (ServletFileUpload.isMultipartContent(request) && request.getContentLength() > -1) {
                new org.elfinder.ElfinderServlet().doPost(request, response)
            }
            else {
                chain.doFilter(request, response)
            }
        } catch (Exception mpE) {
            log.error mpE
        }
    }

    public void destroy() { /* Do nothing */ }
    
}
