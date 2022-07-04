package com.govey.web;

import lombok.NoArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@NoArgsConstructor
@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException exception) throws IOException, ServletException {
        String msg = "Invalid ID or Password";

        if (exception instanceof DisabledException) {
            msg = "DisabledException";
        } else if (exception instanceof CredentialsExpiredException) {
            msg = "CredentialsExpiredException";
        } else if (exception instanceof BadCredentialsException) {
            msg = "BadCredentialsException";
        }
        setDefaultFailureUrl("/login?error=true&exception="+msg);
        super.onAuthenticationFailure(req, res, exception);
    }
}
