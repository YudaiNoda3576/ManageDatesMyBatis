package com.example.demo.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ManageDates;
import com.example.demo.service.ManageDatesService;

@RestController
@RequestMapping("/api")
public class ManageDatesRestController {
	@Autowired
	ManageDatesService manageDatesService;
	
//	全件取得
	@GetMapping
	List<ManageDates>getManageDates() {
		List<ManageDates>manageDates = manageDatesService.findAll();
		return manageDates;
	}
//	一件検索
	@GetMapping("/{id}")
	ManageDates getManageDates(@PathVariable String id) {
		ManageDates manageDates = manageDatesService.findOne(id);
		return manageDates;
	}
	
//	新規登録
	
	
	
}
