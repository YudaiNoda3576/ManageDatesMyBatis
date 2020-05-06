package com.example.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.ManageDates;
import com.example.demo.repository.ManageDatesMapper;

@Service
@Transactional
public class ManageDatesRestService {
//	@Autowired
//	RestTemplate restTemplate;
//
//	public static final String findAll = "http://localhost:8888/findAll";
//	public static final String search = "http://localhost:8888/seach/{id}";
//	public static final String findOne = "http://localhost:8888/findOne/{id}";
//	public static final String insert = "http://localhost:8888/insert";
//	public static final String update = "http://localhost:8888/update/{id}";
//	public static final String delete = "http://localhost:8888/delete/{id}";
//	@Value("http://localhost:8888/put/{id}")
//	private URI putUrl;
//	
//	public List<ManageDatesDto> getAllResponse() {
//		ResponseEntity<List<ManageDatesDto>> response = restTemplate.exchange(findAll, HttpMethod.GET, null,
//				new ParameterizedTypeReference<List<ManageDatesDto>>() {
//		});
//		List<ManageDatesDto> resource = response.getBody();
//		
//		if(CollectionUtils.isEmpty(resource)) {
//			throw new ManageDatesNotFoundException("レコードが存在しません");
//		}
//		return resource;
//	}
//	
//	public List<LocalDate> search(String id) {
//		ResponseEntity<List<LocalDate>> response = restTemplate.exchange(findAll, HttpMethod.GET, null, 
//			new ParameterizedTypeReference<List<LocalDate>>() {
//			}, id);
//		List<LocalDate>result = response.getBody();
//		if(CollectionUtils.isEmpty(result)) {
//			throw new ManageDatesNotFoundException("レコードが存在しません");
//		}
//		return result;
//	}
//	
//	public boolean insert(@Validated @RequestBody ManageDates manageDates) {
//		boolean response = restTemplate.postForObject(insert, manageDates, boolean.class);
//		if(!response) {
//			throw new ManageDatesNotFoundException("保存するレコードが存在しません");
//		} return response;
//	}
//	
//	public ManageDates getId(String id) {
//		ManageDates result = restTemplate.getForObject(findOne, ManageDates.class, id);
//		if(result != null) {
//			throw new ManageDatesNotFoundException("取得するレコードが存在しません");
//		}
//		return result;
//	}
//	
//	public boolean update(@Validated @RequestBody ManageDates manageDates) {
//		RequestEntity<ManageDates> requestEntity = RequestEntity.put(putUrl).body(manageDates);
//		ResponseEntity<Boolean> responseEntity = restTemplate.exchange(requestEntity, boolean.class);
//		boolean result = responseEntity.getBody();
//		if(!result) {
//			throw new ManageDatesNotFoundException("更新するレコードが存在しません");
//		}
//		return result;
//	}
//	
//	public boolean delete(String id) {
//		ResponseEntity<Boolean> response = restTemplate.exchange(delete, HttpMethod.DELETE, null, boolean.class, id);
//		boolean result = response.getBody();
//	 if(!result) {
//		throw new ManageDatesNotFoundException("更新するレコードが存在しません");
//	}
//	return result;
//	}
	
//　RestTemplateを使わないコードの練習です
	@Autowired
	ManageDatesMapper manageDatesMapper;
	
	public List<ManageDates> findAll() {
		return manageDatesMapper.findAll();
	}
	
	public List<LocalDate> search(String input) {
//		入力値をlocalDateに変換
		DateTimeFormatter formatter = new DateTimeFormatterBuilder()
			.appendValue(ChronoField.YEAR, 4)
			.appendValue(ChronoField.MONTH_OF_YEAR, 2)
			.appendValue(ChronoField.DAY_OF_MONTH, 2)
			.toFormatter();
		LocalDate inputDate = LocalDate.parse(input, formatter); 
			
		List<ManageDates> manageDates = manageDatesMapper.findAll();
			
		List<LocalDate> sumDate = new ArrayList<LocalDate>();
//			各レコードから計算用の年月日を取得。入力値と合算させる。
		for(com.example.demo.domain.ManageDates n : manageDates) {
			int dbYear = n.getYear();
			int dbMonth = n.getMonth();
			int dbDate = n.getDate();
			LocalDate sum = inputDate.plusYears(dbYear).plusMonths(dbMonth).plusDays(dbDate);
//			listに計算結果を格納
			sumDate.add(sum);
		}
			return sumDate;
	}

	
//	一件検索
	public ManageDates findOne(String id) {
		try {
			return manageDatesMapper.findOne(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ManageDatesNotFoundException("指定された値が存在しません");
		}
	}
	
// 新規登録
	public void insert(ManageDates manageDates) {
		manageDatesMapper.insert(manageDates);
	}
	
//	一件更新
	public boolean update(ManageDates manageDates) {
		boolean result = manageDatesMapper.update(manageDates);		
		
		if(result) {
			return true;
		} 
			return false;
		}

//	削除
	public boolean delete(String id) {
		boolean result = manageDatesMapper.delete(id);

		if(result) {
			return true;
		} else {
			return false;
		}
	}

	
}
