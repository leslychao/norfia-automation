package com.vkim.skyeng.filter;

import com.vkim.skyeng.dto.AppConfigDto;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class SessionFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) servletRequest;
    HttpSession httpSession = req.getSession();
    AppConfigDto appConfigDto = (AppConfigDto) httpSession.getAttribute("app_config");
    if (appConfigDto == null) {
      appConfigDto = new AppConfigDto();
      httpSession.setAttribute("app_config", appConfigDto);
    }
    appConfigDto.setLastUrl(req.getRequestURI());
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
