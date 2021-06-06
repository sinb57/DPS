package com.project.dps.service;

import com.project.dps.dto.member.MemberDto;
import com.project.dps.domain.member.Member;
import com.project.dps.dto.member.MemberFormDto;
import com.project.dps.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;


    // 회원 가입
    @Transactional
    public Long join(MemberFormDto memberFormDto) {
        validateEmailIsDuplicated(memberFormDto.getEmail()); // 중복 회원 검증

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        memberFormDto.setPassword(encoder.encode(memberFormDto.getPassword()));

        Member member = MemberDto.toEntity(memberFormDto);

        memberRepository.save(member);
        return member.getId();
    }

    public MemberDto findByEmail(String email) {
        return MemberDto.toDto(getMemberIfExist(email));
    }


    private void validateEmailIsDuplicated(String email) {
        // EXCEPTION
        Optional<Member> member = memberRepository.findByEmail(email);
        member.ifPresent(e -> {
            throw new IllegalStateException("Already existed email");
        });
    }



    Member getMemberIfExist(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        // EXCEPTION
        return member.orElseThrow(() -> new IllegalStateException("Not existed member id"));
    }

    Member getMemberIfExist(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        // EXCEPTION
        return member.orElseThrow(() -> new IllegalStateException("Not existed member email"));
    }



    @Override // 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 UserInfo로 반환 타입 지정 (자동으로 다운 캐스팅됨)
    public Member loadUserByUsername(String email) throws UsernameNotFoundException { // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
        return getMemberIfExist(email);
    }
}
