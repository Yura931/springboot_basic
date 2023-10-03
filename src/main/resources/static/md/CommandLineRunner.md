# CommandLineRunner

### 1. CommandLinRunner
> - 스프링 부트 애플리케이션이 실행될 때 자동으로 실행되는 인터페이스
> - 인터페이스를 구현하면 애플리케이션이 시작될 때 특정한 작업을 수행 할 수 있다.
> ```java
> @FunctionalInterface
> public interface CommandLineRunner {
>   void run(String... args) throws Exception;
> }
> ```
> - run 메서드는 애플리케이션을 실행할 때 호출되며, 메서드의 매개변수로는 커맨드 라인에서 전달된 인자들이 배열루 전달된다.
> - CommandLineRunner를 사용하면 애플리케이션을 시작할 때 필요한 초기화 작업이나 배치 작업을 수행할 수 있다.
> - 데이터베이스 초기화, 설정 로딩, 외부 서비스와의 연동 등 처리에 유용 
> ```java
>   import org.springframework.beans.factory.annotation.Autowired;
>   import org.springframework.boot.CommandLineRunner;
>   import javax.persistence.EntityManagerFactory;
> 
>   public class SpringApplication implements CommandLineRunner {
> 
>       @Autowired
>       EntityManagerFactory emf;   // static 메서드에서는 사용 불가능 이런 경우 CommandLineRunner를 구현해 run메서드에서 사용이 가능하다.
>       
>       public static void main(String[] args) {
>           SpringApplication app = new SpringApplication(SpringbootApplication.class);
>           app.run(args);
>       } 
>       
>       @Override
>       public void run(String... args) throws Exception {
>           EntityManager em = emf.createEntityManager();
>
>           System.out.println("emf = " + emf);
>           EntityTransaction tx = em.getTransaction();
>
>           User user = new User();
>           user.setId("aaa");
>           user.setPassword("1234");
>           user.setName("Lee");
>           user.setEmail("aaa@aaa.com");
>           user.setInDate(new Date());
>           user.setUpDate(new Date());
>
>           tx.begin(); // 트랜잭션 시작
>
>           // 1. 저장
>           em.persist(user); // user 엔티티를 em에 영속화(저장)
>
>           // 2. 변경
>           user.setPassword("4321"); // PersistenceContext가 변경감지. update
>           tx.commit();
>
>           // 3. 조회. 영속상태에서 데이터를 가지고 있는 경우 DB까지 조회하지 않음
>           User user2 = em.find(User.class, "aaa"); // em에 있으면 DB조회 안함
>           System.out.println("user==user2 = " + (user == user2));
>           User user3 = em.find(User.class, "bbb"); // em에 없으면 DB조회
>           System.out.println("user3 = " + user3); // null, DB에 없음
>
>           // 4. 삭제
>           // 위에 있는 transaction과 별개
>           tx.begin();
>           em.remove(user); // em에서 user엔티티를 삭제
>           tx.commit();
>       }
>   }
> ```

