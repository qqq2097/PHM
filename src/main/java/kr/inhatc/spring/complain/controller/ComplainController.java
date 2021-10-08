package kr.inhatc.spring.complain.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.inhatc.spring.complain.entity.CPFiles;
import kr.inhatc.spring.complain.entity.Complains;
import kr.inhatc.spring.complain.service.ComplainService;
import kr.inhatc.spring.login.security.SecurityUser;

@Controller
public class ComplainController {

	// log로 불러온 정보를 파일로 저장하기 위해서
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ComplainService complainService;

	@RequestMapping("/complain/profile")
	public String profile() {
		return "profile"; // 프로필
	}

	@RequestMapping(value = "/complain/complainList", method = RequestMethod.GET)
	public String complainList(Model model) {
		List<Complains> list = complainService.complainList();
		model.addAttribute("list", list);

		SecurityUser userComplain = (SecurityUser) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		model.addAttribute("userCpRole", userComplain.getAuthorities());
		model.addAttribute("userCpId", userComplain.getUsername());

		return "complain/complainList";
	}

	// 글쓰기
	@RequestMapping(value = "/complain/complainInsert", method = RequestMethod.GET)
	public String complainWrite() {
		return "/complain/complainWrite";
	}

	// 글쓰기 다음 저장
	@RequestMapping(value = "/complain/complainInsert", method = RequestMethod.POST)
	public String complainInsert(Complains complain, Model model, MultipartHttpServletRequest multipartHttpServletRequest) {
		int hitCnt = complain.getHitCnt();

		SecurityUser user = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("=============>"+user.getUsername());
		
		complain.setCreateId(user.getUsername());
		
		
		System.out.println("=============>"+complain.getCreateId());
		
		model.addAttribute("createId",user.getUsername());
		complainService.saveComplains(complain, multipartHttpServletRequest, hitCnt);
		// 게시판으로 다시 이동
		return "redirect:/complain/complainList";
	}

	// 쓴 글 불러오기
	@RequestMapping(value = "/complain/complainDetail/{complainIdx}", method = RequestMethod.GET)
	public String complainDetail(@PathVariable("complainIdx") int complainIdx, Model model) {
		// 상세정보 가져오기
		Complains complain = complainService.complainDetail(complainIdx);
		model.addAttribute("complain", complain);

		return "complain/complainDetail";
	}

	// 수정하고 업데이트
	@RequestMapping(value = "/complain/complainUpdate/{complainIdx}", method = RequestMethod.POST)
	public String boardUpdate(@PathVariable("complainIdx") int complainIdx, Complains complain) {

		int hitCnt = complain.getHitCnt();

		complain.setComplainIdx(complainIdx);

		complainService.saveComplains(complain, null, hitCnt + 1);
		// 게시판으로 다시 이동
		return "redirect:/complain/complainList";
	}

	// 삭제하기
	@RequestMapping(value = "/complain/complainDelete/{complainIdx}", method = RequestMethod.GET)
	public String complainDelete(@PathVariable("complainIdx") int complainIdx) {
		complainService.complainDelete(complainIdx);
		// 게시판으로 다시 이동
		return "redirect:/complain/complainList";
	}

	// 파일 다운로드
	@RequestMapping(value = "/complain/downloadComplainCPFile", method = RequestMethod.GET)
	public void downloadComplainFile(@RequestParam("idx") int idx, @RequestParam("complainIdx") int complainIdx,
			HttpServletResponse response) throws Exception {

		CPFiles complainFile = complainService.selectFileInfo(idx, complainIdx);

		if (ObjectUtils.isEmpty(complainFile) == false) { // 파일이 있다면
			String fileName = complainFile.getOriginalFileName();

			byte[] files = FileUtils.readFileToByteArray(new File(complainFile.getStoredFilePath()));

			// response 헤더에 설정
			response.setContentType("application/octet-stream");
			response.setContentLength(files.length); // 길이
			response.setHeader("Content-Disposition",
					"attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");

			response.getOutputStream().write(files); // 버퍼 출력
			response.getOutputStream().flush(); // 버퍼에 있는거 밀어내기
			response.getOutputStream().close();
		}
	}

}
