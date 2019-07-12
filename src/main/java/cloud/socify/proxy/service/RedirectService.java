package cloud.socify.proxy.service;

import cloud.socify.proxy.model.Redirect;
import cloud.socify.proxy.model.RedirectHistory;
import cloud.socify.proxy.repository.RedirectHistoryRepository;
import cloud.socify.proxy.repository.RedirectRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RedirectService implements IRedirectService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RedirectRepository redirectRepository;
    @Autowired
    private RedirectHistoryRepository redirectHistoryRepository;

    @Override
    public String getRedirectLink(HttpServletRequest request) {
        RedirectHistory history = new RedirectHistory();

        String requestURI = request.getRequestURI();
        Redirect redirect = redirectRepository.getByLinkFrom(requestURI);
        String redirectUri;
        long redirectId = -1;
        if (redirect == null) {
            redirectUri = "https://www.google.com/";
        } else {
            redirectId = redirect.getId();
            redirectUri = redirect.getLinkTo();
        }

        //redirectId
        history.setRedirectId(redirectId);
        //linkFrom
        history.setLinkFrom(requestURI);
        //linkTo
        history.setLinkTo(redirectUri);
        //ip
        String ipAddress = request.getRemoteHost() + "(" + request.getRemoteAddr() + ")";
        history.setIp(ipAddress);
        //headers
        try {
            String headersStr = objectMapper.writeValueAsString(getHeadersInfo(request));
            history.setHeaders(headersStr);
        } catch (JsonProcessingException e) {
            log.warn(e.getMessage(), e);
        }
        //cookies
        try {
            Cookie[] cookies = request.getCookies();
            String cookiesStr = cookies == null ? "" : objectMapper.writeValueAsString(cookies);
            history.setCookies(cookiesStr);
        } catch (JsonProcessingException e) {
            log.warn(e.getMessage(), e);
        }
        //body
        try {
            String body = CharStreams.toString(request.getReader()) + "";
            history.setBody(body);
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
        }
        //time
        history.setTime(ZonedDateTime.now());

        try {
            redirectHistoryRepository.save(history);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }

        try {
            log.info("Redirect '" + requestURI + "' to '" + redirectUri + "'");
            log.info(objectMapper.writeValueAsString(history));
        } catch (JsonProcessingException ex) {
            log.warn(ex.getMessage(), ex);
        }

        return redirectUri;
    }

    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}
