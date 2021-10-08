package kr.inhatc.spring.complain.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.inhatc.spring.complain.entity.CPFiles;
import kr.inhatc.spring.complain.entity.Complains;

public interface ComplainService {

	List<Complains> complainList();

	void saveComplains(Complains complain, MultipartHttpServletRequest multipartHttpServletRequest, int hitCnt);

	Complains complainDetail(int complainIdx);

	void complainDelete(int complainIdx);
	
	/* void boardFileDelete(int idx, int boardIdx); */

	CPFiles selectFileInfo(int idx, int complainIdx);

}
