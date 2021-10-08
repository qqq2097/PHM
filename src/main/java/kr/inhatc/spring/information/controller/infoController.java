package kr.inhatc.spring.information.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.inhatc.spring.board.service.BoardService;
import kr.inhatc.spring.information.service.InfoService;

//@RestController 결과물을 바로 받아옴
@Controller // html 파일로 넘겨줌
public class infoController {

	
	// log로 불러온 정보를 파일로 저장하기 위해서
		private Logger log = LoggerFactory.getLogger(this.getClass());

		@Autowired
		private InfoService infoService;


		@RequestMapping("information/info")
		public String information() {
			return "information/info"; //공조기정보창
		}
		
		@RequestMapping("/information/profile")
		public String profile() {
			return "profile"; //프로필
		}
	
}
