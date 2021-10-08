package kr.inhatc.spring.chart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.inhatc.spring.chart.entity.Chart;
import kr.inhatc.spring.chart.repository.chartRepository;

@Service
public class chartService {
	
	@Autowired
	private chartRepository respostiory;
	
	//public String savechart(chart chart)
	//{
		//respostiory.save(chart);
		//return "saved chart Resource";
	
		
	//}
	
	public List<Chart> getAllchart()
	{
		return respostiory.findAll();
	}

	public void deleteAll() {
		respostiory.deleteAll();
	}







	

}