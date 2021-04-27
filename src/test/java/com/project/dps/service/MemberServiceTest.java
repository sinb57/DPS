package com.project.dps.service;

import com.project.dps.controller.dto.MemberDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;

    @Test
    public void 로그인() throws Exception {

        //given
        String email = "test@naver.com";
        String name = "Tester111";
        String password = "qwe123!@#";

        MemberDto member1 = createMemberDto(email, name, password);

        Long id1 = memberService.join(member1);

        //when
        Long id2 = memberService.login(email, password);

        //then
        assertThat(id1).isEqualTo(id2);
     }

    @Test
    public void 로그인_잘못된_비밀번호() throws Exception {

        //given
        String email = "test@naver.com";
        String name = "Tester111";
        String password = "qwe123!@#";

        MemberDto member1 = createMemberDto(email, name, password);
        memberService.join(member1);

        //when
        //then
        assertThrows(IllegalStateException.class, () -> {
            memberService.login(email, password+"123");
        });
    }

    @Test
    public void 회원가입() throws Exception {

        //given
        MemberDto member1 = createMemberDto("test1@naver.com", "Tester111", "비밀번호");
        MemberDto member2 = createMemberDto("test2@naver.com", "Tester111", "비밀번호");
        MemberDto member3 = createMemberDto("test3@naver.com", "Tester111", "비밀번호");

        //when
        Long id1 = memberService.join(member1);
        Long id2 = memberService.join(member2);
        Long id3 = memberService.join(member3);

        //then
        MemberDto expected1 = memberService.findOne(id1).get();
        MemberDto expected2 = memberService.findOne(id2).get();
        MemberDto expected3 = memberService.findOne(id3).get();

        assertThat(id1).isEqualTo(expected1.getId());
        assertThat(id2).isEqualTo(expected2.getId());
        assertThat(id3).isEqualTo(expected3.getId());

    }

    @Test
    public void 이메일_중복_예외처리() throws Exception {

        //given
        MemberDto member1 = createMemberDto("test1@naver.com", "Tester111", "비밀번호");
        MemberDto member2 = createMemberDto("test1@naver.com", "Tester222", "비밀번호");

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
         MemberDto member1 = createMemberDto("test1@naver.com", "Tester111", "비밀번호");
         long id = memberService.join(member1);

         //when
         memberService.update(id,"Tester222", "비밀번호", "비밀번호22");
         member1 = memberService.findOne(id).get();

         //then
         assertThat(member1.getName()).isEqualTo("Tester222");
         assertThat(member1.getPassword()).isEqualTo("비밀번호22");
      }

      private MemberDto createMemberDto(String email, String name, String password) {
          return MemberDto.builder()
                  .email(email)
                  .name(name)
                  .password(password)
                  .build();
      }
}