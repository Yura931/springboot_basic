package com.fastcampus.springboot.ch4;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// properties파일에 spring.jpa.hibernate.ddl-auto 가 create로 설정되어 있어서 db관련 테스트 시 테이블 삭제 후 생성되는 현상 생김
// update로 변경하기 보다는 test에 order를 설정해 insert 후 select 할 수 있도록 설정
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @Order(4)
    public void deleteTest() {
        boardRepository.deleteById(1L);

        Board board = boardRepository.findById(1L).orElse(null);
        assertTrue(board == null);
    }

    @Test
    @Order(3)
    public void updateTest() {
        Board board = boardRepository.findById(1L).orElse(null);
        assertTrue(board != null);

        board.setTitle("modified Title");
        boardRepository.save(board);
        Board board2 = boardRepository.findById(1L).orElse(new Board());

        System.out.println("board = " + board);
        System.out.println("board2 = " + board2);

        assertTrue(board.getTitle().equals(board2.getTitle()));
    }


    @Test
    @Order(2)
    public void selectTest() {
        Board board = boardRepository.findById(1L).orElse(null);
        assertTrue(board != null);
    }

    @Test
    @Order(1)
    public void insertTest() {
        Board board = new Board();
        board.setBno(1L);
        board.setTitle("Test Title");
        board.setContent("This is Test");
        board.setWriter("aaa");
        board.setViewCnt(0L);
        board.setInDate(new Date());
        board.setUpDate(new Date());
        boardRepository.save(board);
    }


}