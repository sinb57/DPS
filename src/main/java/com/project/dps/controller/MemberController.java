package com.project.dps.controller;

import com.project.dps.controller.dto.MemberDto;
import com.project.dps.domain.Member;
import com.project.dps.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private int pageSize = 10;

    // 로그인 페이지
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/login";
    }

    @PostMapping("/login")
    public String login(Model model) {
        return "";
    }

    // 회원가입 페이지
    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/join";
    }

    @PostMapping("/join")
    public String join(@Valid MemberDto memberDto, BindingResult result) {

        if (result.hasErrors()) {
            return "member/join";
        }

        Member member = Member.builder()
                .email(memberDto.getEmail())
                .name(memberDto.getName())
                .password(memberDto.getPassword())
                .build();

        memberService.join(member);

        return "member/join-success";
    }

    // 로그아웃 페이지
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        return "";
    }




    // 회원 목록 페이지
    @GetMapping("/list/{pageNo}")
    public Page<Member> listMembers (Model model,
                                     @PathVariable("pageNo") Optional<Integer> pageNo,
                                     @PathVariable("email") String email,
                                     @PathVariable("name") String name) {
        PageRequest request = PageRequest.of(pageNo.isPresent() ? pageNo.get() : 1,
                pageSize, Sort.Direction.DESC, "member_id");
        return memberService.findMembers(request);
    }

    // 회원 검색




    // 회원 조회 페이지
    @GetMapping("/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
        return "";
    }


}
