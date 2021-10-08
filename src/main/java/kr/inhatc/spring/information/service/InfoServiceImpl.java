package kr.inhatc.spring.information.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.thymeleaf.standard.expression.Each;


import kr.inhatc.spring.board.entity.Boards;
import kr.inhatc.spring.board.entity.Files;
import kr.inhatc.spring.board.repository.BoardRepository;
import kr.inhatc.spring.user.entity.Users;
import kr.inhatc.spring.user.repository.UserRepository;
import kr.inhatc.spring.utils.FileUtils;

@Service
public class InfoServiceImpl implements InfoService {

	

}