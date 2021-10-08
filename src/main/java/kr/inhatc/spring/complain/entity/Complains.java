package kr.inhatc.spring.complain.entity;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 데이블을 만드는 엔티티 객체 생성
@Table(name = "t_complain") 
@NoArgsConstructor // 디폴트 생성자
@AllArgsConstructor // 전체 컬럼 생성자
@Data // get, set
@Builder // 객체 생성 도와주는 도구
public class Complains {

	@Id
	@Column(name = "complain_idx")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int complainIdx;
	private String title;
	private String contents;
	private int hitCnt;
	private String createId;
	
  
	// @Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false, columnDefinition = "date default sysdate")
	private OffsetDateTime createDatetime;
	private char deleteYN;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="complainIdx")
	@ElementCollection
	private List<CPFiles> fileList;

}
