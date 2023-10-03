package com.fastcampus.springboot.ch4;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OneToOneTest {

    @Autowired
    public EntityManager em;

    @Autowired
    public CartRepository cartRepo;

    @Autowired
    public MemberRepository memberRepo;

    @Test
    public void userTestTest(@RequestBody UserTestDto.UserTestRequest request) {
        // controller layer
        UserTestDto.UserTestCommand command
                = UserTestDto.UserTestCommand.of(request.getId(), request.getPhoneNumber(), request.getEmail());

        // service layer
        UserTest userTest = new UserTest(command);
//        UserTestRepository.save(userTest);
    }

    @Test
    public void ontoOneTest() {
        Member member = new Member();
        member.setId(1L);
        member.setName("aaa");
        member.setEmail("aaa@aaa.com");
        member.setPassword("1234");
        memberRepo.save(member);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setMember(member);
        cartRepo.save(cart);

        cart = cartRepo.findById(cart.getId()).orElse(null);
        assertTrue(cart != null);
        System.out.println("cart = " + cart);

        member = memberRepo.findById(member.getId()).orElse(null);
        System.out.println("member = " + member);
        assertTrue(member != null);

        // toString 호출 시 상호참조 스택오버플로우 조심
    }
}