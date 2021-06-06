package com.project.dps.controller;

import com.project.dps.dto.member.MemberDto;
import com.project.dps.dto.member.MemberFormDto;
import com.project.dps.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @RequestMapping("/")
    public String request_index() {
        return "member/member";
    }

    // 회원가입 페이지
    @PostMapping("/join")
    public String getMethod_join(MemberFormDto memberFormDto) {
        memberService.join(memberFormDto);
        return "redirect:/";
    }


    // 로그아웃 페이지
    @RequestMapping("/logout")
    public String request_logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }


}
