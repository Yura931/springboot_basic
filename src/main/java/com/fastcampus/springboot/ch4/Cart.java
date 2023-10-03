package com.fastcampus.springboot.ch4;

import org.hibernate.mapping.ToOne;

import javax.persistence.*;

@Entity
public class Cart {

    @Id
    @Column(name="cart_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="member_id", nullable = false) // nullable = false -> inner join
    private Member member;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
//                ", member=" + member +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
