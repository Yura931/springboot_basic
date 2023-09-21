# Spring DI 활용하기 - 이론

### 1. Bean과 ApplicationContext
> - Bean - Spring Container가 관리하는 객체
>   - Spring Container(ApplicationContext)
>     - Bean 저장소, Bean을 저장, 관리(생성, 소멸, 연결) 
>     - 객체 저장소 (Map<key, value>)
>     - BeanFactory - Bean을 생성, 연결 등의 기본 기능을 정의
>     - ApplicationContext - BeanFactory를 확장해서 여러 기능을 추가 정의

### 2. ApplicationContext의 종류
|AC의 종류| XML | Java Config                        |
|---|---|------------------------------------|
|non-Web| GenericXmlApplicationcontext| AnnotationConfigApplicationContext |
|Web|XmlWebApplicationContext| AnnotationConfigWebApplicationContext|
> 자바설정으로 넘어가는 추세

### 3. 스프링 애너테이션 - @ComponentScan, @Component
> @ComponentScan으로 @Component를 자동 검색해서 빈으로 등록
>> ```java
>> @Configuration
>> @ComponentScan("com.fastcampus.ch3") // 패키지 지정
>> @ComponentScan(basePackages = {"com.fastcampust.ch3"})
>> @ComponentScan(basePackageClasses = Appconfig.class) // 클래스 지정
>> @ComponentScan   // 이 애너테이션이 붙은 클래스의 패키지를 자동 스캔
>>``` 
> @Controller, @Service, @Repository, @ControllerAdvice의 메타 애너테이션

### 4. 스프링 애너테이션 - @Value, @PropertySource
> @Value : 값을 하드코딩 하지 않고 저장된 값을 읽어와서 변수 초기화  
> @PropertySource : 읽어올 파일 지정