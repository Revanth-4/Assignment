package ServiceImpl;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;

public class NoOpFilter implements Filter {

    // Called once when the filter is initialized
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No-op filter does nothing here
    }

    // Called for each request, just passes it along the filter chain
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Do nothing, just forward to the next filter in the chain
        chain.doFilter(request, response);
    }

    // Clean up resources (if needed)
    @Override
    public void destroy() {
        // No-op filter does nothing here
    }
}
