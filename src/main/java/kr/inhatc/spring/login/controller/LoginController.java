package kr.inhatc.spring.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class LoginController {
	
@GetMapping("/login/Login")
public void login() {
log.debug("login test 중");
	
} 

@GetMapping ("/login/accessDenied")
public void accesDenied() { //로그인 실패시 이동할 화면
	
}

@GetMapping ("/login/logout")
public void logout() { //로그인 실패시 이동할 화면
System.out.println("=======>로그아웃");
}
}
