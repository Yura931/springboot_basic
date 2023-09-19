package com.fastcampus.springboot.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

@Controller
public class YoilTeller {
    @RequestMapping("/getYoil")
    public void main(HttpServletRequest request, HttpServletResponse response) throws IOException { // 요청 정보를 담고 있는 request, 응답에 사용할 객체 response
        // SOLID 원칙 지켜서 프로그래밍 하기!!!
        // 1. 입력
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String day = request.getParameter("day");

        int yyyy = Integer.parseInt(year);
        int mm = Integer.parseInt(month);
        int dd = Integer.parseInt(day);

        // 2. 작업 - 요일을 계산
        Calendar cal = Calendar.getInstance(); // 현재 날짜와 시간을 갖는 cal
        cal.clear();    // cal 모든 필드 초기화
        cal.set(yyyy, mm - 1, dd);  // 월(mm)은 0부터 11이기 때문에 1을 빼줘야 함.

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1 ~ 7을 반환. 1: 일요일, 2: 월요일
        char yoil = "일월화수목금토".charAt(dayOfWeek - 1);

        // 3. 출력 - 작업 결과를 부라우저에 전송
        response.setCharacterEncoding("ms949"); // 한글 윈도우 MS949

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("year = " + year);
        out.println("month = " + month);
        out.println("day = " + day);
        out.println("yoil = " + yoil);
        out.println("</body>");
        out.println("</html>");
    }

    @RequestMapping("/getYoil2")
    public void getYoil2(String year, String month, String day, HttpServletResponse response) throws IOException { // ReflectionAPI를 사용, 매개변수 명과 일치하는 data를 얻어올 수 있음

        // 1. 입력
        int yyyy = Integer.parseInt(year);
        int mm = Integer.parseInt(month);
        int dd = Integer.parseInt(day);

        // 2. 작업 - 요일을 계산
        Calendar cal = Calendar.getInstance(); // 현재 날짜와 시간을 갖는 cal
        cal.clear();    // cal 모든 필드 초기화
        cal.set(yyyy, mm - 1, dd);  // 월(mm)은 0부터 11이기 때문에 1을 빼줘야 함.

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1 ~ 7을 반환. 1: 일요일, 2: 월요일
        char yoil = "일월화수목금토".charAt(dayOfWeek - 1);

        // 3. 출력 - 작업 결과를 부라우저에 전송
        response.setCharacterEncoding("ms949"); // 한글 윈도우 MS949

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("year = " + year);
        out.println("month = " + month);
        out.println("day = " + day);
        out.println("yoil = " + yoil);
        out.println("</body>");
        out.println("</html>");
    }

    @RequestMapping("/getYoil3")
    public void getYoil3(int year, int month, int day,
                         HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 입력 관심사 분리, 작업과 출력 관심사만 남음

        // 2. 작업 - 요일을 계산
        Calendar cal = Calendar.getInstance(); // 현재 날짜와 시간을 갖는 cal
        cal.clear();    // cal 모든 필드 초기화
        cal.set(year, month - 1, day);  // 월(mm)은 0부터 11이기 때문에 1을 빼줘야 함.

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1 ~ 7을 반환. 1: 일요일, 2: 월요일
        char yoil = "일월화수목금토".charAt(dayOfWeek - 1);

        // 3. 출력 - 작업 결과를 부라우저에 전송
        response.setCharacterEncoding("ms949"); // 한글 윈도우 MS949

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("year = " + year);
        out.println("month = " + month);
        out.println("day = " + day);
        out.println("yoil = " + yoil);
        out.println("</body>");
        out.println("</html>");
    }

    @RequestMapping("/getYoil4")
    public String getYoil4(int year, int month, int day, Model model) throws IOException {

        // 입력 관심사 분리

        // 2. 작업 - 요일을 계산
        Calendar cal = Calendar.getInstance(); // 현재 날짜와 시간을 갖는 cal
        cal.clear();    // cal 모든 필드 초기화
        cal.set(year, month - 1, day);  // 월(mm)은 0부터 11이기 때문에 1을 빼줘야 함.

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1 ~ 7을 반환. 1: 일요일, 2: 월요일
        char yoil = "일월화수목금토".charAt(dayOfWeek - 1);

        // 출력 관심사 분리, 작업한 결과를 담은 Model객체 전달 (DS가 Model을 View로 전달)
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("yoil", yoil);

        return "yoil";  // yoil.html - 뷰의 이름을 반환
    }

    @RequestMapping("/getYoil5")
    public ModelAndView getYoil5(int year, int month, int day) throws IOException {

        // ModelAndView 사용
        ModelAndView mv = new ModelAndView();
        mv.setViewName("yoilError");

        if(!isValid(year, month, day)) {
            return mv;
        }
        // 2. 작업 - 요일을 계산
        Calendar cal = Calendar.getInstance(); // 현재 날짜와 시간을 갖는 cal
        cal.clear();    // cal 모든 필드 초기화
        cal.set(year, month - 1, day);  // 월(mm)은 0부터 11이기 때문에 1을 빼줘야 함.

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1 ~ 7을 반환. 1: 일요일, 2: 월요일
        char yoil = "일월화수목금토".charAt(dayOfWeek - 1);

        // 출력 관심사 분리, 작업한 결과를 담은 Model객체 전달 (DS가 Model을 View로 전달)
        mv.addObject("year", year);
        mv.addObject("month", month);
        mv.addObject("day", day);
        mv.addObject("yoil", yoil);

        mv.setViewName("yoil");
        return mv;
    }

    private boolean isValid(int year, int month, int day) {
        return true;
    }

    @RequestMapping("/getYoil6")
    public String getYoil6(@ModelAttribute MyDate myDate, Model model) throws IOException { // @ModelAttribute("myDate"/생략 가능, 생략 시 객체의 앞글자를 소문자로 변경해 사용) 객체를 Model에 저장해주는 어노테이션, 참조형에만 붙일 수 있음, 기본형은 불가능
        // 코드가 아주 많이 간결해 짐

        char yoil = getYoil(myDate);

        return "yoil";  // yoil.html - 뷰의 이름을 반환
    }

    @ModelAttribute("yoil") // 메서드에 붙이면 메서드의 호출 결과가 정해준 이름으로 저장 됨
    private char getYoil(MyDate myDate) {
        Calendar cal = Calendar.getInstance(); // 현재 날짜와 시간을 갖는 cal
        cal.clear();    // cal 모든 필드 초기화
        cal.set(myDate.getYear(), myDate.getMonth() - 1, myDate.getDay());  // 월(mm)은 0부터 11이기 때문에 1을 빼줘야 함.

        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1 ~ 7을 반환. 1: 일요일, 2: 월요일
        char yoil = "일월화수목금토".charAt(dayOfWeek - 1);
        return yoil;
    }
}
