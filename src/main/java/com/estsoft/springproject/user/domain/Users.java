package com.estsoft.springproject.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Entity
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public Users(){

    }

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }


    // 각 사용자의 권한 정보를 넣어주면 됨
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  // 인가 - 권한
        // * 인가 처리
        // 각 사용자의 권한 리턴 "ROLE_ADMIN"
        // 접근 권한
        // GET  /articles/{id}
        return List.of(new SimpleGrantedAuthority("user"),new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 접속날짜 expired
        return true;
    }

    // 계정의 잠김 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정의 pw정보 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
