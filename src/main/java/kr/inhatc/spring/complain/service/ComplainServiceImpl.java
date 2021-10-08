package kr.inhatc.spring.complain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.inhatc.spring.complain.entity.Complains;
import kr.inhatc.spring.complain.entity.CPFiles;
import kr.inhatc.spring.complain.repository.ComplainRepository;
import kr.inhatc.spring.utils.FileUtils;

@Service
public class ComplainServiceImpl implements ComplainService {

	@Autowired
	ComplainRepository complainRepository;

	@Autowired
	private FileUtils fileUtils;

	@Override
	public List<Complains> complainList() {
		List<Complains> list = complainRepository.findAllByOrderByComplainIdxDesc();
		return list;
	}

	@Override
	public void saveComplains(Complains complain, MultipartHttpServletRequest multipartHttpServletRequest, int hitCnt) {
		
		complain.setHitCnt(hitCnt);

		/*
		 * List<Files> list = fileUtils.parseFileInfo(multipartHttpServletRequest);
		 * if(CollectionUtils.isEmpty(list)==false){ complain.setFileList(list); }
		 */

		complainRepository.save(complain);

	}

	@Override
	public Complains complainDetail(int complainIdx) {

		Optional<Complains> optional = complainRepository.findById(complainIdx);

		if (optional.isPresent()) {// id가 존재한다면
			Complains complain = optional.get();
			complain.setHitCnt(complain.getHitCnt() + 1);
			complainRepository.save(complain);

			return complain;
		} else { // 없을때는
			throw new NullPointerException();
		}

	}

	@Override 
	public void complainDelete(int complainIdx) {
		complainRepository.deleteById(complainIdx);
	}




//	@Override
//	public void boardFileDelete(int idx, int boardIdx) {
//		boardRepository.deleteBoardFile(idx, boardIdx);
//	}

	@Override
	public CPFiles selectFileInfo(int idx, int complainIdx) {
		CPFiles complainFile = complainRepository.selectCPFileInfo(idx, complainIdx);
		return complainFile;
	}

}
