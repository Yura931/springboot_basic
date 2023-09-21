# redirect와 forwoard

### 1. redirect와 forward의 처리 과정 비교

> #### redirect
> /ch2/write.jsp 요청 -> request  
> write.jsp -> 로그인을 해야한다는 응답 -> 번지수 잘못 찾았으니 /ch2/login.jsp 이 경로로 가라는 응답  
> /ch2/login.jsp 브라우저 자동요청 -> new request  
> login.jsp 응답  
>> redirect : 2번요청, 2번응답  
>> 첫 요청은 GET, POST 다 가능, 브라우저 자동요청은 GET요청만 가능   
>> 1번과 3번의 request객체는 다른 객체

> #### forward
> /ch2/write.jsp 요청 -> request  
> write.jsp request 그대로 받아서 -> encode.jsp 전달  
> 응답
>> forward : 1번요청, 1번응답

### 2. RedirectView
> /ch2/register/save 요청 -> DispatcherServlet -> Controller (redirect:/register/add)
> -> DispatcherServlet -> RedirectView -> 응답

### 3. HTTP 요청과 요청 방법
> 1. URL 직접입력으로 요청(GET)
> 2. 링크 \<a>로 요청(GET)
> 3. 폼 \<form>으로 요청(GET, POST)
> 4. redirect - 다른 URL로 이동(GET), 자동 재요청. 브라우져 URL 변경됨
> 5. forward - 요청(GET, POST)을 다른 URL로 전달. 브라우져 URL 변경 안됨
 