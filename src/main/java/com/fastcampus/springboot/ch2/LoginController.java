package com.fastcampus.springboot.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping(value="/login")
    public String login(HttpServletRequest request, String id, String pwd, Model model, RedirectAttributes rttr) throws Exception {
        // 1. id, pwd를 확인
        if(loginCheck(id, pwd)) {
            model.addAttribute("id", id);
            model.addAttribute("pwd", pwd);
            return "userInfo";  // userInfo.html
        } else {
//            String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");
            String msg = "id 또는 pwd가 일치하지 않습니다.";
            rttr.addAttribute("msg", msg);
            rttr.addFlashAttribute("msg", "일회용 메시지");
            request.setAttribute("msg", "request에 저장된 msg"); // redirect시 읽을 수 없는 request 메세지
            return "forward:/";
//            return "redirect:/login/login?msg="+msg; // GET, URL재작성
        }
        // 2. 일치하면, userInfo.html
        // 일치하지 않으면, login.html로 이동
    }

    private boolean loginCheck(String id, String pwd) {
        return id.equals("asdf") && pwd.equals("1234");
    }
}
