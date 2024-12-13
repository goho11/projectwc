package com.metacoding.projectwc.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Table(name = "user_tb")
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 이메일 주소 아이디로 사용
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    // 논리 삭제
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDeleted;

    @Builder
    public User(Integer id, String email, String password, String nickname, Timestamp createdAt, boolean isDeleted) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }

    @PrePersist
    public void prePersist() {
        if (isDeleted == null)
            isDeleted = false;
    }

    public void update(String nickname, String password) {
        this.nickname = nickname;
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
        return List.of();
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
