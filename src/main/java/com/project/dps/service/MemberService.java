package com.project.dps.service;

import com.project.dps.domain.Member;
import com.project.dps.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired MemberRepository memberRepository;

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
    public Long join(Member member) {
        validateNameIsDuplicated(member.getEmail()); // 중복 회원 검증
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
    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Page<Member> findMembers(Pageable pageable) {
        int pageNo = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        int elementCount = 10;

        pageable = PageRequest.of(pageNo, elementCount, Sort.Direction.DESC, "id");
        return memberRepository.findAll(pageable);
    }

    // 회원 조건 조회 (by email)
    public List<Member> findMembersByEmail(String email) {
        return memberRepository.findByEmailLike(email);
    }

    public Page<Member> findMemberByEmail(String email, Pageable pageable) {
        return memberRepository.findByEmailLike(email, pageable);
    }

    // 회원 조건 조회 (by name)
    public List<Member> findMembersByName(String name) {
        return memberRepository.findByNameLike(name);
    }

    public Page<Member> findMemberByName(String email, Pageable pageable) {
        return memberRepository.findByNameLike(email, pageable);
    }

}
