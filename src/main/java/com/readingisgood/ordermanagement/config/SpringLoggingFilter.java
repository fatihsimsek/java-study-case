package com.readingisgood.ordermanagement.config;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static java.util.stream.Collectors.toMap;

@Component
public class SpringLoggingFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(SpringLoggingFilter.class);

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final SpringRequestWrapper wrappedRequest = new SpringRequestWrapper(request);
        logger.info("http_request: method={}, uri={} ,headers={}, params={} ,payload={}",
                wrappedRequest.getMethod(),
                wrappedRequest.getRequestURI(),
                Collections.list(request.getHeaderNames()).stream().collect(toMap(hn -> hn, request::getHeader)),
                Collections.list(request.getParameterNames()).stream().collect(toMap(pa -> pa, request::getParameter)),
                IOUtils.toString(wrappedRequest.getInputStream(),
                        wrappedRequest.getCharacterEncoding()));

        final SpringResponseWrapper wrappedResponse = new SpringResponseWrapper(response);
        chain.doFilter(wrappedRequest, wrappedResponse);

        stopWatch.stop();
        logger.info("http_response({} ms): method={}, uri={}, status={}, payload={}",
                stopWatch.getLastTaskInfo().getTimeMillis(),
                wrappedRequest.getMethod(),
                wrappedRequest.getRequestURI(),
                wrappedResponse.getStatus(),
                IOUtils.toString(wrappedResponse.getContentAsByteArray(), wrappedResponse.getCharacterEncoding()));

    }
}
