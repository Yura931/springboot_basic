# Querydsl 동적 쿼리

### 1. Querydsl
> - JPQL과 SQL은 문자열이므로 타입이나 구문 체크가 어려움
> - 쿼리를 타입에 안전하게 빌드해주는 JPA Creteria API등장. 길고 읽기 어려움
> - JPA Criteria API(표준)의 단점을 보완하기 위해 Querydsl(오픈소스)이 등장

### 2. JPAQuery와 JPAQueryFactory
> - Q Type : Entity를 기반으로 자동 생성된 클래스
> - JPQLQuery : JPQL쿼리를 위한 인터페이스
> - JPAQuery : JPLQuery의 구현체. 직접 생성하거나 JPAQueryFactory를 통해 생성
> - JPAQueryFactory : JPAQuery를 생성해 주는 클래스.

### 3. JPAQueryFactory 쿼리 작성
> - 쿼리 작성에 JPAQueryFactory, Q타입과 Q타입 필드의 메서드를 이용

### 4. BooleanBuilder로 동적 쿼리 작성
> - BooleanBuilder를 이용하면 조건에 따라 쿼리가 달라지게 할 수 있다.


