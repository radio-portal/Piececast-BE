package org.nhnacademy.piececast.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhnacademy.piececast.member.domain.Member;
import org.nhnacademy.piececast.member.info.CustomOAuth2User;
import org.nhnacademy.piececast.member.repository.MemberRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // Spotify에서 받는 기본 정보
        String email = (String) attributes.get("email");
        String username = (String) attributes.get("display_name");

        log.info("OAuth2 attributes: {}", attributes);

        // DB에 이메일로 조회 후 없으면 새로 저장
        Member member = memberRepository.findByEmail(email)
                .orElseGet(() -> {
                    Member newMember = Member.builder()
                            .username(username != null ? username : email)
                            .email(email)
                            .password("") // OAuth 사용자에 대해선 빈값 가능
                            .role("USER")
                            .build();
                    try {
                        return memberRepository.save(newMember);
                    } catch (Exception e) {
                        log.error("회원 저장 실패", e);
                        throw e;
                    }
                });


        return new CustomOAuth2User(member, attributes);
    }
}
