package kr.inhatc.spring.chart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.inhatc.spring.chart.entity.Chart;

@Repository
//데이터 조작, jpaRepository 상속받음 상속받은 값은 매핑할 entity와 id 타입
public interface chartRepository extends JpaRepository<Chart, Integer>{
//	List<chart> findAll();
	public List<Chart> findTop5ByOrderByTimeDesc();
	public Chart findTop1ByOrderByTimeDesc();
}
