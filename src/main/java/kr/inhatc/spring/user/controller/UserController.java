package kr.inhatc.spring.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.inhatc.spring.user.entity.Users;
import kr.inhatc.spring.user.repository.UserRepository;
import kr.inhatc.spring.user.service.UserService;

@Controller
//@RequestMapping("/user")
public class UserController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	//@RequestMapping("/")
	//public String hello() {
		// log.debug("=========================>" + "여기 !"); // 디버그로 찍는거
		//return "/login/Login";
	//}
	
	
	@RequestMapping(value= "/", method=RequestMethod.POST)
	public String Login() {
		return "login/Login"; //로그인, 회원가입 화면	
		}
	
	@RequestMapping(value= "/login/loginSuccess")
	public String Login_suc() {
		return "redirect:/Home"; //로그인, 회원가입 화면	
		}
	
	@RequestMapping("/Home")
	public String hello() {
		return "/Home"; //대시보드
	}

	
	
	// GET(읽을때), POST(만들때), PUT(업데이트할때), DELETE(삭제할때) /@GetMapping 등등 붙여서 쓸수도 있음
	//@GetMapping("/userList") // 위에 RequestMapping과 연결됨
	@RequestMapping(value = "/user/userList", method=RequestMethod.GET)
	public String userList(Model model, 
			@PageableDefault(size = 2)org.springframework.data.domain.Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String searchText) {
		// boardDto랑 같은 기능 `
//		return userService.findByName(PageRequest.of(1, 2, Direction.DESC, "userId"));
//		userRepository.findByName(PageRequest.of(1, 2));		
//		List<Users> list = userService.userList();
//		Page<Users> list = userRepository.findAll(pageable);
		Page<Users> list = userRepository.findByIdContainingOrNameContaining(searchText,searchText,pageable);

		int startPage = Math.max(1, list.getPageable().getPageNumber() - 4);
		int endPage = Math.min(list.getTotalPages(), list.getPageable().getPageNumber() + 4);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("list", list);
		// 뷰어 이동
		return "user/userList";
	}
	
//	@GetMapping({"/test", "test2"})
//	public void test() {
//		
//	}
	
	//@GetMapping("/userInsert") // 위에 RequestMapping과 연결됨
	@RequestMapping(value = "/user/userInsert", method=RequestMethod.GET)
	public String userWrite() {
		// 뷰어 이동
		return "user/userWrite";
	}
	
	//@GetMapping("/userInsert") // 위에 RequestMapping과 연결됨
	@RequestMapping(value = "/user/userInsert", method=RequestMethod.POST)
	public String userInsert(Users user) {
		if(user != null) {
			System.out.println("변경 전 :" + user.getPw());
			//encode
			String pw = encoder.encode(user.getPw());
			System.out.println("변경 후 :" + pw);
			user.setPw(pw);
			userService.saveUsers(user);
		}
		// 뷰어 이동
		return "redirect:/user/userList";
	}
	
	

	// 회원가입
	@RequestMapping(value = "/login/signUp", method=RequestMethod.POST)
	public String user_Insert(Users user) {
		if(user != null) {
			System.out.println("변경 전 :" + user.getPw());
			//encode
			String pw = encoder.encode(user.getPw());
			System.out.println("변경 후 :" + pw);
			user.setPw(pw);
			userService.saveUsers(user);
		}
		// 뷰어 이동
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	// = @GetMapping
	@RequestMapping(value = "/user/userDetail/{id}", method=RequestMethod.GET)//userList.html에서 받아온 id값 
	public String userDetail(@PathVariable("id") String id, Model model) {
		Users user = userService.userDetail(id);
		model.addAttribute("user", user); // 웹에서 불러온 user, 참조변수
		//System.out.println("===================================>" + user);
		// 뷰어 이동 
		return "user/userDetail";
	}
	
	@RequestMapping(value = "/user/userUpdate/{id}", method=RequestMethod.POST)//userList.html에서 받아온 id값 
	public String userUpdate(@PathVariable("id") String id, Users user) {
		
		user.setId(id);
		
		userService.saveUsers(user);
		return "redirect:/user/userList";
	}
	
	@RequestMapping(value = "/user/userDelete/{id}", method=RequestMethod.GET)
	public String userDelete(@PathVariable("id") String id) {
		userService.userDelete(id);
		return "redirect:/user/userList";
	}
	

}
