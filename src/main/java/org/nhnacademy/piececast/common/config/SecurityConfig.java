package org.nhnacademy.piececast.common.config;

import org.springframework.context.annotation.Bean;

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    ...
    http.authorizeRequests(authorizeRequests -> authorizeRequests
                    .antMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated()
            )
            .oauth2Login(oauth2Login -> oauth2Login
                    .loginPage("/auth/redirect-to-spotify")
                    .userInfoEndpoint(userInfoEndpoint ->
                            userInfoEndpoint.userService(customOauth2UserService)
                    )
                    .successHandler(oAuth2AuthenticationSuccessHandler)
                    .failureHandler(oAuth2AuthenticationFailureHandler)
            )
            .addFilterBefore(new JwtAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

    return http.build();
}