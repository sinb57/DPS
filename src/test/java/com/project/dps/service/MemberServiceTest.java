package com.project.dps.service;

import com.project.dps.domain.Member;
import com.project.dps.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void 로그인() throws Exception {

        //given
        String email = "test@naver.com";
        String name = "Tester";
        String password = "qwe123!@#";

        Member member1 = new Member(email, name, password);
        memberService.join(member1);

        //when
        Long id = memberService.login(email, password);

        //then
        assertThat(id).isEqualTo(member1.getId());
     }

    @Test
    public void 로그인_잘못된_비밀번호() throws Exception {

        //given
        String email = "test@naver.com";
        String name = "Tester";
        String password = "qwe123!@#";

        Member member1 = new Member(email, name, password);
        memberService.join(member1);

        //when
        //then
        assertThrows(IllegalStateException.class, () -> {
            Long id = memberService.login(email, password+"123");
        });
    }

    @Test
    public void 회원가입() throws Exception {

        //given
        Member member1 = new Member("test1@naver.com", "이름", "비밀번호");
        Member member2 = new Member("test2@naver.com", "이름", "비밀번호");
        Member member3 = new Member("test3@naver.com", "이름", "비밀번호");

        //when
        memberService.join(member1);
        memberService.join(member2);
        memberService.join(member3);

        //then
        Member expected1 = memberService.findOne(member1.getId()).get();
        Member expected2 = memberService.findOne(member2.getId()).get();
        Member expected3 = memberService.findOne(member3.getId()).get();

        assertThat(member1).isEqualTo(expected1);
        assertThat(member2).isEqualTo(expected2);
        assertThat(member3).isEqualTo(expected3);

    }

    @Test
    public void 이메일_중복_예외처리() throws Exception {

        //given
        Member member1 = new Member("test1@naver.com", "이름", "비밀번호");
        Member member2 = new Member("test1@naver.com", "이름", "비밀번호");

        //when
        memberService.join(member1);

        //then
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });

     }

     @Test
     public void 회원정보_변경() throws Exception {

         //given
         Member member1 = new Member("test1@naver.com", "이름", "비밀번호");
         memberService.join(member1);

         //when
         memberService.update(member1.getId(),"이름22", "비밀번호", "비밀번호22");

         //then
         assertThat(member1.getName()).isEqualTo("이름22");
         assertThat(member1.getPassword()).isEqualTo("비밀번호22");
      }
}