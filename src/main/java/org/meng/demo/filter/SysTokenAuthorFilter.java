package org.meng.demo.filter;

import org.meng.demo.common.exception.DemoException;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * 过滤器
 *
 * @author
 * @date 2018-09-21 09:26:30
 */
@WebFilter(urlPatterns = "/*")
@Order(value = 10)
public class SysTokenAuthorFilter implements Filter {

    /**
     * 不过滤的url
     */
    private static final Set<String> ALLOWED_PATHS = Collections
            .unmodifiableSet(new HashSet<>(Arrays.asList(
                    "/api/login",
                    "/api/loginOut",
                    "fsfsfsd"
            )));

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        res.setCharacterEncoding("UTF-8");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        // 判断是否需要过滤
        String path = request.getRequestURI().substring(request.getContextPath().length())
                .replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);
        //不需要过滤
        if (allowedPath) {
            chain.doFilter(req, res);
        } else {
            try {
                //如果符合过滤，就获取token
                String token = request.getHeader("token");
                if (null == token || token.isEmpty()) {
                    throw new ServletException("需要登录再请求");
                } else {
                    chain.doFilter(req, res);
                }
            } catch (DemoException e) {
                e.printStackTrace();
                //throw new ServletException("token=null,不合法的请求.");
            }
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

}
