package com.metacoding.projectwc.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("login-form")
    public String loginForm() {
        return "login-form";
    }

    @GetMapping("main")
    public String main() {
        return "main";
    }

    @GetMapping("mine")
    public String mine() {
        return "mine";
    }

    @GetMapping("game")
    public String game() {
        return "game";
    }

    @GetMapping("rank")
    public String rank() {
        return "rank";
    }

    @GetMapping("result")
    public String result() {
        return "result";
    }

    @GetMapping("signup-form")
    public String signupForm() {
        return "signup-form";
    }

    @GetMapping("start-form")
    public String startForm() {
        return "start-form";
    }

    @GetMapping("user-form")
    public String userForm() {
        return "user-form";
    }

//    @GetMapping("wc-form")
//    public String wcForm() {
//        return "wc-form";
//    }
}
