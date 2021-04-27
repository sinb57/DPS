package com.project.dps.controller;

import com.project.dps.controller.dto.MemberDto;
import com.project.dps.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    //TODO:: Session

    private final MemberService memberService;

    // 로그인 페이지
    @GetMapping("/login")
    public String getMethod_login(Model model) {
        model.addAttribute("memberDto", MemberDto.builder().build());
        System.out.printf("haha");
        return "member/login";
    }

    @PostMapping("/login")
    public String postMethod_login(Model model) {
        return "";
    }

    // 회원가입 페이지
    @GetMapping("/join")
    public String getMethod_join(Model model) {
        model.addAttribute("memberDto", MemberDto.builder().build());
        return "member/join";
    }

    @PostMapping("/join")
    public String postMethod_join(@Valid MemberDto memberDto, BindingResult result) {

        if (result.hasErrors()) {
            System.out.printf("member join error");
            return "member/join";
        }

        memberService.join(memberDto);

        return "member/join-success";
    }

    // 로그아웃 페이지
    @RequestMapping("/logout")
    public String request_logout(HttpServletRequest request) {
        return "";
    }


    // 회원 목록 페이지
    @GetMapping("/list")
    public String getMethod_list(@PageableDefault Pageable pageable, Model model) {
        Page<MemberDto> memberDtoList = memberService.findMembers(pageable);
        model.addAttribute("memberDtoList", memberDtoList);

        return "member/memberList";
    }

    // 회원 검색


    // 회원 조회 페이지
    @GetMapping("/detail/{userName}")
    public String getMethod_detail(@PathVariable Integer id, Model model) {
        return "member/memberDetail";
    }


}
