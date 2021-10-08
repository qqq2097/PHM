package kr.inhatc.spring.user.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 				// 데이블을 만드는 엔티티 객체 생성
@Table(name = "users") 	// 테이블명 다르게 줌('users' 로)
@NoArgsConstructor 		// 디폴트 생성자
@AllArgsConstructor 	// 전체 컬럼 생성자
@Data 					// get, set
@Builder				// 객체 생성 도와주는 도구
public class Users {
	
	// 테이블이 없다면 알아서 create 함
	@Id 						// primary 키 잡기 / @GeneratedValue 숫자용
	@Column(name = "USER_ID") 	// 디비버의 테이블 컬럼명을 바꿈
	private String id;
	private String pw;
	// @Column(length = 20) 길이 20으로 주기
	private String name;
	private String email;
	  
	// date 추가
	@Temporal(TemporalType.TIMESTAMP) // 데이터베이스에서 DATE 타입으로 주기
	// insertable(false) => 읽기전용 / updatable => 업데이트 가능여부 / columnDefinition => 컬럼 정의(sysdate로 준다)
	@Column(insertable = false, updatable = false, columnDefinition = "date default sysdate") 
	private Date joinDate;
	private String enabled;
	
	@Column(name="ROLE")
	private String role = "ROLE_MEMBER";

}
