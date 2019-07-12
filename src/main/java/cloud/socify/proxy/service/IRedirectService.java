package cloud.socify.proxy.service;

import javax.servlet.http.HttpServletRequest;

public interface IRedirectService {

    String getRedirectLink(HttpServletRequest request);
}
