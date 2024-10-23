package com.estsoft.springproject.domain;

import jakarta.persistence.*;

import java.util.concurrent.locks.Lock;

@Entity
public class Members {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    // 멤버측에서 FK를 들고 있으므로 주체는 Members
    private Locker locker;
}
