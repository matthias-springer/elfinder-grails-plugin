import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

import org.apache.commons.fileupload.servlet.ServletFileUpload
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.elfinder.ElfinderServlet

class UploadFilter implements Filter {

    private Log log = LogFactory.getLog(getClass())

    void init(FilterConfig filterConfig) throws ServletException { /* Do nothing */ }

    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            if (ServletFileUpload.isMultipartContent(request) && request.getContentLength() > -1) {
                new ElfinderServlet().doPost(request, response)
            }
            else {
                chain.doFilter(request, response)
            }
        } catch (Exception mpE) {
            log.error mpE
        }
    }

    void destroy() { /* Do nothing */ }
}
