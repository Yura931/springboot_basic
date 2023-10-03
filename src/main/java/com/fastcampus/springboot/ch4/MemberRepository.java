package com.fastcampus.springboot.ch4;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, Long> {

}
