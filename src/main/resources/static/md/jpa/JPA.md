# JPA의 개요와 설정

### 1. JPA(Java Persistence API)
> - ORM(Object-Relational Mapping: 객체와 RDB를 매칭)을 위한 표준 Java 표준 API  
> - 인터페이스 집합
> - JPA 구현체 : HIVERNATE(Spring boot 주 사용), OpenJpa, EclipseLink, DataNucleus,...

### 2. ORM(Object/Relational Mapping) Framework - HIVERNATE
> - 객체와 DB테이블 간의 연결을 해주는 프레임 워크  
> - 객체 모델(object model)과 관계형 모델(relational model)의 차이를 해소

### 3. 객체 모델과 관계형 모델의 비교
| 항목  | 객체 모델(object model)        | 관계형 모델(relational model) |
|-----|----------------------------|--------------------------|
|구조| 나누기 쉬움                     |나누기 어려움|
|상속| 지원                         |지원안함|
|비교| 동일 비교(==)와 동등 비교(equals()) |동일 비교(==)|
|조회| 그래프(객체간의 참조 연결)            |테이블(2차원 데이터)|
|관계| 단방향(단방향x2로 양방향처리)          |양방향(한쪽 테이블에만 FK지정)|

### 4. SQL Mapper(MyBatis) vs. ORM framework(HIDVERNATE)
> - SQL Mapper
>   - DB 중심 개발
>   - SQL 직접 작성
>   - 성능, 복잡한 일처리에는 좋음
>   - 오래된 기업들은 기존에 쌓여있는 데이터를 바꾸기 쉽지 않음
> - ORM framework(HIBERNATE)
>   - 애플리케이션 중심 개발
>   - 자바클래스 작성 -> Table 자동 생성
>   - 자바 코드 수정하면 자동으로 변경 됨
>   - 변경사항이 줄어듬
>   - 객체 모델만 관리하면 된다는 장점!

### 5. Spring Data와 Spring Data JPA 
> - Spring Data
>   - 저장소 종류가 달라도 일관된 데이터 처리 방법을 제공
>   - Repository
>   - CrudRepository
>   - PagingAndSortingRepository
> - Spring Data JPA
>   - JPA를 위한 저장소(JpaRepository)와 관련 기능을 제공
>   - JpaRepository
