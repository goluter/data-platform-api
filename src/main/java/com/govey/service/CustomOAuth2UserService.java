package com.govey.service;

import com.govey.domain.user.User;
import com.govey.domain.user.UserRepository;
import com.govey.dto.OAuth2UserAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = loadUser(userRequest);

        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String registrationId = clientRegistration.getClientId();
        String userNameAttributeName = clientRegistration.getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuth2UserAttributes attributes = OAuth2UserAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveUser(attributes);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveUser(OAuth2UserAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
