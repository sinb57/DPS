package com.project.dps.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
public class MemberDto implements Serializable {

    private Long id;

    @NotNull
    @Pattern(regexp = "[a-zA-z0-9]+@[a-zA-z]+[.]+[a-zA-z.]+")
    private String email;

    @NotNull
    @Size(min=6, max=12)
    private String name;

    @NotNull
    //@Pattern(regexp = "")
    private String password;
}
