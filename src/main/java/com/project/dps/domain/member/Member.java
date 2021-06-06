package com.project.dps.domain.member;

import com.project.dps.domain.log.ScenarioLog;
import com.project.dps.domain.log.StageLog;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    private String password;

    private String auth;

    @OneToMany(mappedBy = "member")
    private List<StageLog> stageLogList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ScenarioLog> scenarioLogList = new ArrayList<>();


    //== Builder 메서드 ==//
    @Builder
    public Member(String email, String name, String password, String auth) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.auth = auth;
    }


    //== 비즈니스 로직 ==//
    public void appendStageLog(StageLog stageLog) {
        this.stageLogList.add(stageLog);
    }

    public void appendScenarioLog(ScenarioLog scenarioLog) {
        this.scenarioLogList.add(scenarioLog);
    }

    public void edit(String name, String password) {
        this.name = name;
        this.password = password;
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}