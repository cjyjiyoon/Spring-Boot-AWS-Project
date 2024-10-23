package com.estsoft.springproject.domain;

import com.estsoft.springproject.entity.Member;
import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.util.StringTokenizer;

@Entity
public class Locker {
    @Id
    @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "locker")
    //
    //  연관관계의 주체가 되는 객체를 명시
    private Members members;
}
