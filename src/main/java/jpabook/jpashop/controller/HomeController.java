package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home(HttpServletRequest request){

        HttpSession session =request.getSession();
        Member mem = (Member) session.getAttribute("loginUser");

        return "home";
    }
}
