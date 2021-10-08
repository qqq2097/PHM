package kr.inhatc.spring.login.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import kr.inhatc.spring.user.entity.Users;

public class SecurityUser extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Users user;

	public SecurityUser(Users user) {
		
		// 암호화 처리 전에 {noop} - 암호화하지 않아도 로그인 가능
		super(user.getId(), user.getPw(), AuthorityUtils.createAuthorityList(user.getRole()));
		this.user = user;
	}



}
