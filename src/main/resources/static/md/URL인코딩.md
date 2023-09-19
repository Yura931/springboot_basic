# URL인코딩

### 1. URL인코딩 - 퍼센트 인코딩
> - URL에 포함된 non-ASCII문자를 문자 코드(16진수) 문자열로 변환
>>
>> 장유라 -> URLEncoder.encode() -> %EC%9E%A5%EC%9C%A0%EB%9D%BC  
>>  URLDecoder.decode() -> 장유라  

| ! | #   | $   | %   | &   |'| (   | )   | *   | +   | ,   | /   | :   | ;   | =   | ?   | @   | [   | ]   |
|---|-----|-----|-----|-----|---|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|-----|
|%21|%23|%24|%25|%26|%27|%28|%29|%2A|%2B|%2C|%2F|%3A|%3B|%3D|%3F|%40|%5B|%5D|

| 문자 |UTF-8|
|---|---|
| 장 |0xEC9EA5|
| 유 |0xEC9CA0|
| 라 |0xEB9DBC|

> - 16진수 0 ~ 9, A ~ F  
> - 숫자를 1byte씩 잘라서 앞에 %를 붙임
 
> 클라이언트에서 요청 시 브라우저가 UTF-8로 자동으로 변환해서 보내줌    
> 서버에서 받아서 getParameter()읽기 전에 request.setCharacterEncoding("UTF-8"); 해주어야 함  
> 스프링은 characterEncoding filter를 제공함

### 2. URL Rewriting - URL 다시쓰기
> URL을 변경하는 것.
> 주로 URL에 쿼리 스트링을 추가
> ```java
> String msg = URLEncoder.encode("ID 또는 비밀번호가 일치하지 않습니다.", "utf-8");
> return "redirect:/login/login?msg="+msg;
> ```
> 브라우저를 통해 입력한 것이 아니기 때문에 수동으로 인코딩을 처리해야 한다.
