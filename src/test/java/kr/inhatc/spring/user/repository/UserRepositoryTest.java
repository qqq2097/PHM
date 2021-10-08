package kr.inhatc.spring.user.repository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.inhatc.spring.user.entity.Users;

@SpringBootTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	//@Test
	public void testInsert() {
		Users user = Users.builder()
				.id("admin")
				.pw("111")
				.email("abc")
				.enabled("사용")
				.role("ROLE_ADMIN")
				.build();
		
		userRepository.save(user);
	}
	
	
	@Test
	public void testGet() {
		Optional<Users> result = userRepository.findById("aaa");
		if(result.isPresent()) {
			Users user = result.get();
			System.out.println("이름11 : " + user.getName());
		}
	}

}
