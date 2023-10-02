# ENtityManager와 EntityManagerFactory

### 1. EntityManager, EntityManagerFactory
> - EntityManagerFactory : EntityManager를 생성. 애플리케이션에 하나
> - EntityManager : Entity를 저장 관리. 사용자당 하나  
> - 사용자는 직접 DB에 명령내리는 대신, EntityManager로만 작업  
> - 저장은 persist(), 조회는 find(), 삭제는 remove(), 변경은 Entity의 setter를 이용

### 2. Entity클래스의 작성
> - Entity클래스 : DB테이블의 한 행(row)을 정의한 것
> - Entity클래스를 작성하고 @Entity를 붙인다.
> - Entity클래스에서 키(PK)로 사용할 속성에 @Id를 붙인다.
> - Entity에 적은 iv와 table 컬럼의 순서보장 안 됨
> - DB에서는 대소문자 구분x

### 3. Entity클래스의 작성을 위한 애너테이션
> - @Entity
> - @Id
> - @Table
> - @Column
> - @Enumerated
> - @Temporal
> - @Transient
> - @Generated

### 4. Entity Transaction
>```java
>   EntityManager entityManager = entityManagerFactory.createEntityManager();
>   EntityTransaction tx = entityManager.getTransaction();
>```

### 5. Transaction
> - 더 이상 나눌 수 없는 작업의 단위
> - 계좌 이체의 경우, 출금과 입금이 하나의 Tx로 묶여야 됨.

### 6. 커밋, 롤백
> - 커밋(commit) - 작업 내용을 DB에 영구적으로 저장
> - 롤백(rollback) - 최근 변경사항을 취소(마지막 커밋으로 복귀)

### 7. PersistenceContext(PC 저장공간)
> - Entity를 저장하는 공간. persist(entity)는 entity를 영속 상태로 변경
> - 캐시, 변경 감지, 지연 로딩, 트랜잭션 지원

### 8. flush(), tx.commit()
> - persist()는 Entity를 영속 상태로 바꾸고, SQL저장소에 INSERT문을 저장
> - flush()는 SQL저장소에 누적된 SQL을 DB로 전달. tx.rollback() 가능
> - tx.commit()은 작업 내용을 DB에 영구 반영(flush() 자동 호출). tx.rollback() 불가

### 9. PersistenceContext의 캐시
> - 실제 Entity가 저장되는 공간(Map).
> - Entity의 @Id 컬럼을 Key로 사용
> - 캐시에서 Entity를 먼저 조회
> - 캐시에 없을 때만 DB에서 조회