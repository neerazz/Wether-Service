package com.neeraj.assignment.security;

import com.neeraj.assignment.exception.CustomAuthenticationException;
import com.neeraj.assignment.model.CustomUserDetails;
import com.neeraj.assignment.model.TokenUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private CustomAuthenticationValidator validator;

    @Autowired
    private CustomUserDetails customUserDetails;

    @Autowired
    private TokenUserDetails tokenUserDetails;

    private final Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        log.trace("additionalAuthenticationChecks");
    }

    @Override
    protected UserDetails retrieveUser(String username,
                                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        log.trace("retrieveUser");

        CustomAuthenticationToken jwtAuthenticationToken = (CustomAuthenticationToken) usernamePasswordAuthenticationToken;
        String token = jwtAuthenticationToken.getToken();

        try {
            tokenUserDetails = validator.validate(token);
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList(tokenUserDetails.getRole());
            customUserDetails.setUserName(tokenUserDetails.getUserName()).setUserID(tokenUserDetails.getId()).setToken(token)
                    .setAuthorities(grantedAuthorities);
        } catch (Exception e) {
            throw new CustomAuthenticationException("Kindly provide a token. The token field is empty.");
        }
        return customUserDetails;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        log.trace("supports");
        return (CustomAuthenticationToken.class.isAssignableFrom(aClass));
    }

}