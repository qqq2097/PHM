package kr.inhatc.spring.complain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.inhatc.spring.complain.entity.CPFiles;
import kr.inhatc.spring.complain.entity.Complains;

@Repository
public interface ComplainRepository extends CrudRepository<Complains, Integer>{

	List<Complains> findAllByOrderByComplainIdxDesc();
	
	@Query("SELECT file FROM Files file WHERE complain_idx = :complainIdx AND idx = :idx")
	CPFiles selectCPFileInfo(@Param("idx") int idx, @Param("complainIdx") int complainIdx);
	
	
}
