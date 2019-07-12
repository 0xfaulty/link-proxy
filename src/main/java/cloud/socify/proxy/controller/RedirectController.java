package cloud.socify.proxy.controller;

import cloud.socify.proxy.service.IRedirectService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class RedirectController {

    private final IRedirectService redirectService;

    @GetMapping(path = "**")
    public RedirectView trade(HttpServletRequest request) {
        String redirectLink = redirectService.getRedirectLink(request);
        return new RedirectView(redirectLink);
    }
}