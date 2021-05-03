package com.project.dps.controller.dto;

import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class MemberDto {

    private Long id;

    @NotBlank(message = "이메일은 필수 입니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "이름은 필수 입니다.")
    @Size(min=6, max=12, message="이름은 6 ~ 12자리로 입력해주세요.")
    private String name;

    @NotBlank(message = "비밀번호은 필수 입니다.")
    //@Pattern(regexp = "")
    private String password;


    // 생성자 메서드
    public MemberDto(Long id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }

}
