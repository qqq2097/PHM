package kr.inhatc.spring.chart.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.inhatc.spring.chart.entity.Chart;
import kr.inhatc.spring.chart.repository.chartRepository;
import kr.inhatc.spring.chart.service.chartService;
import lombok.extern.slf4j.Slf4j;
//controller 역할: 사용자 http 요청 진입 지점, 사용자에게 서버에서 처리된 데이터를 view와 함께 응답
@RestController //RestController는 뷰 페이지로 이동하지 않고 json과 같은 데이터 응답받고자 할 때 정의
@Slf4j
public class chartController {

	@Autowired
	private chartRepository repo;
	
	@Autowired
	private chartService service;
	
	
	@PostMapping("/Home5")
	public List<Chart> Home()
	{
		List<Chart> t = repo.findTop5ByOrderByTimeDesc();
		
//		for (int i = 0; i < t.size(); i++) {
//			String temp = t.get(i).getTime().format(DateTimeFormatter.ofPattern("ss"));
//			t.get(i).setTime(LocalDateTime.parse(temp,DateTimeFormatter.ofPattern("ss")));
//		} //for
		
		return repo.findTop5ByOrderByTimeDesc();
		
	} //Home();
	
	@PostMapping("/Home1")
	public Chart Home2()
	{
		return repo.findTop1ByOrderByTimeDesc();
	}
	
	@GetMapping("/test")
	public String deleteAll()
	{
		Chart s = new Chart(100, 0.5F, LocalDateTime.now(), 3, 0, null, 0.05F);
		try {
			repo.save(s);
		} catch (Exception e) {
			log.error(e.toString()+"----------------------------------");
		}
		
		List<Chart> c= service.getAllchart();
		
		return c.size()+"";
	}
}