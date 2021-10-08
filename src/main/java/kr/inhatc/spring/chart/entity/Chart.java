package kr.inhatc.spring.chart.entity;

import java.time.LocalDateTime;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// entity 역할: 데이터베이스 테이블과 매핑되는 객체

@Cacheable(false)
@Entity
@Table(name="test")
@Builder
@NoArgsConstructor // 디폴트 생성자
@AllArgsConstructor // 전체 컬럼 생성자
@Data // get, set, toString
public class Chart {
	@Id 
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "data1") 	// 디비버의 테이블 컬럼명을 바꿈
	public int data1;
	
//	@Column(name="realdata")
	public float realdata;
	
//	@Column(columnDefinition="DATETIME(0) default CURRENT_TIMESTAMP")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
	public LocalDateTime time;
	
	public int anomaly;
	public int flag;
	public String pattern;
	
	public double mfcc;
}