package org.zerock.w2.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Log4j2
@WebFilter(urlPatterns={"/*"})
public class UTF8Filter implements Filter {
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    log.info("UTF8Filter ................");

    // request의 인코딩 변경을 위한 request 취득
    HttpServletRequest req = (HttpServletRequest)servletRequest;
    // request의 인코딩을 UTF-8로 변경
    req.setCharacterEncoding("UTF-8");

    filterChain.doFilter(servletRequest, servletResponse);
  }
}











