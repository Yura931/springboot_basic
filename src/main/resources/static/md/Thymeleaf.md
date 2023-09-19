# thymeleaf 알아보기
**https://www.thymeleaf.org/**

### 1. 타임리프(thymeleaf)란?
> - 자바 웹개발에 이상적인 '모던 서버 사이드 자바 템플릿 엔진'  
> - HTML과 유사해서 디자이너와 개발자간의 협업을 쉽게 해준다.  
> - 확장성이 뛰어나며, 커스터마이징이 쉽다.  
> - 다양한 도구와 확장 프로그램으로 구성된 에코 시스템 제공
>   - https://www.thymeleaf.org/ecosystem.html

### 2. 타임리프 템플릿
> - 타임리프 템플릿(*.html)은 HTML과 유사해서 편집 후 내용 확인이 쉽다.
> - th:* 속성은 타임리프 전용 속성이며, 브라우저는 이를 무시한다.

### 3. th:text와 th:utext
> th:text는 ${...}을 해성해서 태그의 텍스트 노드로
> ```html
>   <span th:text="${lastName}">Yura</span>
>   <span>[[${lastName}]]</span>
> ```
> 문자열('...') 결합(+)과 리터럴 치환(|...|)  
> 출력해야 하는 텍스트가 여러개일 때 문자열 결합 해주어야 함  
> +연산자 보다 ||연산자를 사용하는 것이 더 편리
> ```html
>   <span th:text="'My name is' + ${lastName} + ', ' + ${firstName}"></span> 
>   <span th:text="|My name is ${lastName}, ${firstName}|"></span>
> ```

### 4. th:utext는 태그의 <,>를 &lt; &gt;로 바꾸지 않고 그대로
> ```html
>   <span th:text="${'<i>Yura</i>'}">Yura</span>        
>   <span th:utext="${'<i>Yura</i>'}">Yura</span> 
> ```
> th:if, th:unless, th:switch로 조건부 처리  
> 특정 조건에 맞는 경우만 처리
>> 참일 경우만
>>```html
>><tr th:if="${list.size}==0">
>>   <td>게시물이 없습니다.</td>
>></tr>
>>```
>
>> 아닌 경우
>>```html
>><tr th:unless="${list.size}!=0">
>>   <td>게시물이 없습니다.</td>
>></tr>
>>```
>
>> 삼항 연산자
>>```html
>><tr th:class="${status.even} ? 'even' : 'odd'">
>>
>></tr>
>>```
>
>> switch case
>>```html
>> <div th:switch="${user.grade}"> <!-- <th:block>태그로도 가능 -->
>>      <span th:case"A"></span>
>>      <span th:case"B"></span>
>>      <span th:case"C"></span>
>>      <span th:case"*"></span>
>> </div>
>>```

### 5. th:each와 th:block을 이용한 반복(1)
> Iterable의 반복 처리는 th:each 또는 th:block 사용. 향상된 for문과 유사
>> ```html
>>  <select multiple>
>>      <option th:each="opt:${list}" th:value="${opt}">[[${opt}]]</option>
>>  </select>
>> ```
> th:each를 쓰기 어려운 경우, th:block으로 처리  
> input 태그 속성에 each사용하기가 어려운 경우가 있어서 th:block으로 전체를 감싸서 사용하기도 함
>> ```html
>>  <th:block th:each="opt:${list}">
>>      <input type="checkbox" th:value="${opt}">[[${opt}]]<br> 
>>  </th:block>
>> ``` 

### 5. th:each와 th:block을 이용한 반복(2) - status변수
> 반복 관련 정보(index, count, size, odd, even, first, last, current) 제공
>>```html
>>  <select multiple>
>>      <option th:each="opt,status : ${list}" th:value="${opt}">
>>          [[${status.index}]].[[${opt}]]
>>      </option>
>>  </select>
>>``` 
> status변수의 선언을 생략하면, '변수명 + Stat' 을 사용
>>```html
>>  <select multiple>
>>      <option th:each="opt : ${list}" th:value="${opt}" th:selected="${optStat.first}">
>>          [[${status.index}]].[[${opt}]]
>>      </option>
>>  </select>
>>``` 

### 6. th:attr와 th:attrappend, th:attrprepend로 속성 값 설정하기
> th:attr은 속성의 값을 설정하는데 사용
>> ```html
>>  <img src="images/dummy.png" th:attr="src=@{images/cat.png}"/>
>>                 ⬇️
>>  <img src="images/cat.png" />
>> ```
> 대부분의 속성(attribute)은 th:속성이름도 가능
>> ```html
>>  <img src="images/dummy.png" th:src="@{/images/cat.png}"/>
>> ```
> th:attrappend, th:attrprepend로 기존의 속성 값에 새로운 값을 추가 가능
>> ```html
>>  <input type="button" value="Go" class="btn" th:attrappend="class=${''+style}" /> <!-- style = new style -->
>>                 ⬇️
>>  <input type="button" value="Go" class="btn new-style" /> 
>> ```

### 7. URL링크 - @{...}
> @{...}를 경로로 변환. '/'로 시작할 때는 context root를 추가
>> ```html
>>  <a href="boardList.html" th:href="@{/board/list}"></a>
>>                 ⬇️
>>  <a href="/ch2/board/list"></a>
>> ```
> Query parameter와 Path variable(여러 개일 때는 구분자 ',' 사용)
>> ```html
>>  <a href="board.html" th:href="@{/board/read(bno=${bno}, type='L')}"></a>
>>                 ⬇️
>>  <a href="/ch2/board/read?bno=123&type=L"></a>  
>> 
>>  <a href="board.html" th:href="@{/board/{bno}/read(bno=${bno})}"></a>
>>                 ⬇️
>>  <a href="/ch2/board/123/read"></a>
>> ```

### 8. 주석(comment)
> `<!-- -->` : HTML 주석. 주석 내의 부분은 타임리프가 처리 안함.  
> `<!--/* */-->` : parser-level 주석. parser가 처리할 때 무시. 에러가 있어도 OK  
> `<!--/*/ /*/-->` : prototype-only 주석. html에서는 주석이지만, 처리되면 주석 아님
>> ```html
>>  <!--/*/ <th:block th:each="opt : ${list}"> /*/ --> 타임리프가 처리, html에서는 주석처럼 보임, 타임리프 템플릿을 html스럽게 하기위함
>> ``` 

### 9. 자바스크립트 인라이닝
> `[[${...}]]`를 자바스크립트에 맞게 적절히 변환해주는 편리한 기능
>>```html
>> <script th:inline="javascript">
>>      var firstName = [[$firstName}]]
>>      var lastName = /*[[$lastName}]]*/ "testName"
>>      var arr = [[${list}]]
>>      var userObj = [[${user}]]
>> </script>️
>> ```
>> ⬇️
>> ```javascript
>> <script>
>>      var firstName = "Jang"
>>      var lastName = "Yura"
>>      var arr = ["aaa", "bbb", "ccc", "ddd", "eee", "fff"]
>>      var userObj = {"name":"aaa", "age":20}
>> </script>

### 10. 유틸리티 객체
> 유용한 메서드를 제공하는 객체들. 변환 & 형식화를 쉽게
> - 문자열 & 숫자 : #strings, #numbers
> - 날짜 & 시간 : #dates, #calendars, #temporals
> - 배열 & 컬렉션 : #arrays, #lists, #sets, #maps
> - 기타 
>   - #uris - URI/URL의 escape/unescape 처리
>   - #conversions - 스프링의 변환기능(ConversionService) 지원
>   - #messages - 자바의 메시지 형식화 국제화 지원
>   - #objects - null 확인 기능 제공
>   - #bools - boolean연산(and, or) 기능 제공
>
> [참고] https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#appendix-b-expression-utility-objects
> [참고] https://www.thymeleaf.org/apidocs/thymeleaf/3.0.0.RELEASE/index.html?overview-summary.html

### 11. 기본 객체
> 서블릿의 기본 객체(request, session, application 등)에 접근방법을 제공  
>> request
>> ```html
>>  <h1 th:text="|year  : ${year}|"></h1>
>>  <h1 th:text="|year  : ${#request.getAttribute('year')}|"></h1>
>>```
>> session
>> ```html
>>  <h1 th:text="|id    : ${session.id}|"></h1>
>>  <h1 th:text="|id    : ${#session.getAttribute('id')|"></h1>
>>```
>> application
>> ```html
>>  <h1 th:text="|email : ${application.email}|"></h1>
>>  <h1 th:text="|email : ${#servletContext.getAttribute('email')}|"></h1>
>>```