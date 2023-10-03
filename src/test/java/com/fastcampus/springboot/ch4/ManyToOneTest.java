package com.fastcampus.springboot.ch4;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ManyToOneTest {

    @Autowired
    public EntityManager em;

    @Autowired
    public BoardRepository boardRepo;

    @Autowired
    public UserRepository userRepo;

    @Test
    public void manyToOneTest() {
        // 1. 테스트 데이터 작성
        User user = new User();
        user.setId("aaa");
        user.setPassword("1234");
        user.setName("LEE");
        user.setEmail("aaa@aaa.com");
        user.setInDate(new Date());
        user.setUpDate(new Date());
        userRepo.save(user);

        Board b1 = new Board();
        b1.setBno(1L);
        b1.setTitle("title1");
        b1.setContent("content1");
        b1.setUser(user);
        b1.setViewCnt(0L);
        b1.setInDate(new Date());
        b1.setUpDate(new Date());
        boardRepo.save(b1);

        Board b2 = new Board();
        b2.setBno(2L);
        b2.setTitle("title2");
        b2.setContent("content2");
        b2.setUser(user);
        b2.setViewCnt(0L);
        b2.setInDate(new Date());
        b2.setUpDate(new Date());
        boardRepo.save(b2);

        b1 = boardRepo.findById(1L).orElse(null);
        b2 = boardRepo.findById(2L).orElse(null);

        System.out.println("b1 = " + b1);
        System.out.println("b2 = " + b2);


        assertTrue(b1 != null);
        assertTrue(b2 != null);

        user = userRepo.findById(user.getId()).orElse(null);    // LAZY 설정 시 user.getList() 호출로 목록 가져올 수 있음. 이 경우 @Transactional을 붙여 하나의 테스트를 하나의 트랜잭션 내에서 동작하도록 해주어야 함
        System.out.println("user = " + user);
        assertTrue(user != null);
    }
}