package com.fastcampus.springboot;

import com.fastcampus.springboot.ch4.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Arrays;
import java.util.Date;

//@Component
//@Conditional(OSCondition.class)
//class ConditionTrueTest {
//    public String toString() {
//        return "ConditionTrueTest{}";
//    }
//}

@Component
@Conditional(FalseCondition.class)
class ConditionFalseTest {
    @Override
    public String toString() {
        return "ConditionFalseTest{}";
    }
}

class TrueCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return true;
    }
}


class FalseCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return false;
    }
}

class OSCondition implements  Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        System.out.println("System.getProperties() = " + System.getProperties());
        return env.getProperty("sum.desktop").equals("windows");
    }
}

@SpringBootApplication
@ServletComponentScan
public class SpringbootApplication implements CommandLineRunner {

    @Autowired
    EntityManagerFactory emf;

    public static void main(String[] args) {
        // web app 사용하지 않음, 톰캣 안 뜸
        SpringApplication app = new SpringApplication(SpringbootApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
/*
        ApplicationContext ac = SpringApplication.run(SpringbootApplication.class, args);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        Arrays.sort(beanDefinitionNames);
        Arrays.stream(beanDefinitionNames)
                .filter(b -> !b.startsWith("org"))
                .forEach(System.out::println);
*/
    }

    @Override
    public void run(String... args) throws Exception {
        EntityManager em = emf.createEntityManager();

        System.out.println("emf = " + emf);
        EntityTransaction tx = em.getTransaction();

        User user = new User();
        user.setId("aaa");
        user.setPassword("1234");
        user.setName("Lee");
        user.setEmail("aaa@aaa.com");
        user.setInDate(new Date());
        user.setUpDate(new Date());

        tx.begin(); // 트랜잭션 시작

        // 1. 저장
        em.persist(user); // user 엔티티를 em에 영속화(저장)

        // 2. 변경
        user.setPassword("4321"); // PersistenceContext가 변경감지. update
        tx.commit();

        // 3. 조회. 영속상태에서 데이터를 가지고 있는 경우 DB까지 조회하지 않음
        User user2 = em.find(User.class, "aaa"); // em에 있으면 DB조회 안함
        System.out.println("user==user2 = " + (user == user2));
        User user3 = em.find(User.class, "bbb"); // em에 없으면 DB조회
        System.out.println("user3 = " + user3); // null, DB에 없음

        // 4. 삭제
        // 위에 있는 transaction과 별개
        tx.begin();
        em.remove(user); // em에서 user엔티티를 삭제
        tx.commit();
    }

}
