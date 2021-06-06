package com.project.dps.dto.member;

import com.project.dps.domain.log.ScenarioLog;
import com.project.dps.domain.log.StageLog;
import com.project.dps.domain.member.Member;
import com.project.dps.dto.log.ScenarioLogDto;
import com.project.dps.dto.log.StageLogDto;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MemberFormDto {

    @NotBlank(message = "이메일은 필수 입니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "이름은 필수 입니다.")
    private String name;

    @NotBlank(message = "비밀번호은 필수 입니다.")
    private String password;

    private String auth;


    //== Builder 메서드 ==//
    @Builder
    public MemberFormDto(String email, String name, String password, String auth) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.auth = auth;
    }

    //== Mapper 메서드 ==//

    public static MemberFormDto toDto(Member e) {
        return MemberFormDto.builder()
                .email(e.getEmail())
                .name(e.getName())
                .password(e.getPassword())
                .auth("user")
                .build();
    }

    public static Member toEntity(MemberFormDto d) {
        return Member.builder()
                .email(d.getEmail())
                .name(d.getName())
                .password((d.getPassword()))
                .auth("user")
                .build();
    }


    public void setPassword(String password) {
        this.password = password;
    }
}
