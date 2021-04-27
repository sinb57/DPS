package com.project.dps.service;

import com.project.dps.controller.dto.MemberDto;
import com.project.dps.domain.Member;
import com.project.dps.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import com.project.dps.mapstruct.MemberMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper mapper = Mappers.getMapper(MemberMapper.class);

    // 로그인
    public Long login(String email, String password) {
        Member member = getMemberIfExist(email);
        validatePasswordIsCorrect(member, password);
        return member.getId();
    }

    public Member getMemberIfExist(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        // EXCEPTION
        return member.orElseThrow(() -> new IllegalStateException("Not existed member id"));
    }


    // 회원 가입
    @Transactional
    public Long join(MemberDto memberDto) {
        Member member = mapper.toEntity(memberDto);
        validateNameIsDuplicated(memberDto.getEmail()); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 회원 검증
    public void validateNameIsDuplicated(String email) {
        // EXCEPTION
        Optional<Member> member = memberRepository.findByEmail(email);
        member.ifPresent(e -> {
            throw new IllegalStateException("Already existed email");
        });
    }


    // 회원 정보 수정
    @Transactional
    public void update(Long id, String name, String passwordFrom, String passwordTo) {
        Member member = getMemberIfExist(id);
        validatePasswordIsCorrect(member, passwordFrom);
        member.modifyNameAndPassword(name, passwordTo);
    }

    public Member getMemberIfExist(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        // EXCEPTION
        return member.orElseThrow(() -> new IllegalStateException("Not existed member id"));
    }

    public void validatePasswordIsCorrect(Member member, String passwordFrom) {
        String originPassword = member.getPassword();
        // EXCEPTION
        if (!originPassword.equals(passwordFrom)) {
            throw new IllegalStateException("Password is not correct");
        }
    }



    // 특정 회원 조회
    public Optional<MemberDto> findOne(Long id) {
        return memberRepository.findById(id)
                .map(member -> mapper.toDto(member));
    }

    // 회원 전체 조회
    public Page<MemberDto> findMembers(Pageable pageable) {
        int pageNo = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        int elementCount = 10;

        pageable = PageRequest.of(pageNo, elementCount, Sort.Direction.DESC, "id");
        return memberRepository.findAll(pageable)
                .map(member -> mapper.toDto(member));
    }

    // 회원 조건 조회 (by email)
    public Page<MemberDto> findMemberByEmail(String email, Pageable pageable) {
        return memberRepository.findByEmailLike(email, pageable)
                .map(member -> mapper.toDto(member));
    }

    // 회원 조건 조회 (by name)
    public Page<MemberDto> findMemberByName(String email, Pageable pageable) {
        return memberRepository.findByNameLike(email, pageable)
                .map(member -> mapper.toDto(member));
    }


}
