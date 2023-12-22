package com.wildbitsfoundry.filterstudio.configuration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

public class AuthenticationService {

    public static Authentication getAuthentication(HttpServletRequest request, String authTokenHeaderName,
                                            String authToken) {
        String apiKey = request.getHeader(authTokenHeaderName);
        if (apiKey == null || !apiKey.equals(authToken)) {
            throw new BadCredentialsException("Invalid API Key");
        }
        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}
