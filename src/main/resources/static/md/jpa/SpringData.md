# Spring Data JPA

### 1. Spring Data, Spring Data JPA
> - Spring Data : 저장소 종류가 달라도 일관된 데이터 처리 방법을 제공
>   - Repository > CrudRepository > PagingAndSortingRepository > JpaRepository
>   - 인터페이스만 정의해주면 Spring Data JPA가 자동으로 구현해 줌
> - Spring Data JPA : JPA를 위한 저장소(JpaRepository)와 관련 기능을 제공

# 쿼리 메서드
### 1. JPA가 지원하는 쿼리
> - JPA는 쿼리를 작성하는 다양한 방법을 지원
>   - JPQL : DB테이블이 아닌 entity를 대상으로 쿼리를 작성. SQL과 유사(대소문자 구별)
>     - SELECT b  FROM Board b WHERE b.title = ?1
>   - 쿼리 메서드(Query Method) : 메서드 이름으로 JPQL을 자동 생성
>     - List<Board> lis = boardRepo.findBytitleAndWriter("title1", "writer1")
>   - JPA Criteria : JPQL을 메서드의 조합으로 작성.
>     - cq.select(b).where(cb.equal(b.get("title"),"title1));
>   - Querydsl : JPQL을 메서드의 조합으로 작성. Criteria보다 간결. 오픈소스
>     - List<Board> list = queryFactory.selectFrom(board).where(board.title.eq("title1")).fetch();
>   - Native SQL : JPQL대신 SQL을 직접 작성. 복잡한 SQL 작성 가능 - @Query

### 2. 쿼리 메서드(Query Method)
> - Spring Data에서 제공하는 기능. 메서드 이름으로 JPQL을 자동 생성
> - Repository에 메서드 이름을 규칙에 맞게 추가. 
> - ex) find + (entity명) + By + 속성명
> ```java
>   public interface BoardRepository extends CrudRepository<Board, Long> {
>       // SELECT COUNT(*) FROM BOARD WHERE WRITER = writer
>       int countAllByWriter(String writer);
>       
>       @Transactional // 여러 문장이 실행되는 경우가 있기 때문에 transaction처리
>       in deleteByWriter(String writer);
>   }
> ```
> - [참고] https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
> - [참고] https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords


# JPQL, 네이티브 쿼리
### 1. JPQL
> - DB테이블이 아닌 entity를 대상으로 쿼리를 작성. SQL과 유사
> - 자동 생성 : 쿼리 메서드(Spring Data), JPA Criteria(JPA 표준), Querydsl(오픈 소스, Criteria가 불편해서 나온 오픈소스)
> - 수동 생성 : em.createQuery(), @Query(JPQL, Native SQL 둘 다 작성가능)
>   - 구문의 경우 문자열로 되어있는데 구문체크가 어려워 intellij같은 IDE에서 체크하는 기능을 제공

### 2. JPQL 작성하기
> - EntityManager의 createQuery()
>```java
>   public interface BoardRepository extends CrudRepository<Board, Long> {
>       String query = "SELECT b FROM b";   
>       TypedQuery<Board> tQuery = em.createQuery(querty, Board.class); // 엔티티 지정
>       List<Board> list = tQuery.getResultList();
>   }  
>```
> - @Query로 작성 - 메서드 이름은 쿼리와 상관 없음
>```java
>   public interface BoardRepository extends CrudRepository<Board, Long> {
>       @Query("SELECT b FROM Board b") // org.springframework.data.jpa.repository.Query
>       List<Board> list = tQuery.getResultList();  // List<Board> list = boardRepo.findAllBoard();
>   }
>```

### 3. JPQL 매개변수 지정
> - 매개변수 순서(default) - ?1은 첫 번째, ?2는 두 번째, ...
>```java
>   public interface BoardRepository extends CrudRepository<Board, Long> {
>       @Query("SELECT b FROM Board b WHERE b.title=?1 AND b.writer=?2")
>       List<Board> findByTitleAndWriter(String title, String writer);
>   }
>```
> - 매개변수 이름 - @Param("이름")으로 바인딩. 생략하면 매개변수 이름으로 바인딩
>```java
>   public interface BoardRepository extends CrudRepository<Board, Long> {
>       @Query("SELECT b FROM Board b WHERE b.title=:title AND b.writer=:writer")
>       List<Board> findByTitleAndWriter(@Param("title") String title,@Param("writer") String writer);
>   }
>```

### 4. 네이티브 쿼리(Native Query)
> - @Query로 네이티브 쿼리(DB에 종속된 쿼리)를 작성 가능. JPQL이 아님
>```java
>   public interface BoardRepository extends CrudRepository<Board, Long> {
>       @Query(value="SELECT * FROM Board", nativeQuery=true)
>       List<Board> findAllBoard();
>   }
>```
> - 일부 컬럼만 조회할 때는 반환 타입을 List<Object[]>로
>```java
>   public interface BoardRepository extends CrudRepository<Board, Long> {
>       @Query(value="SELECT TITLE, WRITER FROM Board", nativeQuery=true)
>       List<Object[]> findTitleAndWriter();
>   }
>```

### 5. 페이징과 정렬 - Pageable, Sort
> 페이징 - @Query붙은 메서드에 Pageable타입의 매개변수 추가
> 정렬 - 오름차순(Sort.Direction.DESC), 내림 차순(Sort.Direction.ASC)

