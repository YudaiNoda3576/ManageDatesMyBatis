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
import com.example.demo.domain.ManageDates;
import com.example.demo.repository.ManageDatesMapper;

@Service
public class ManageDatesService {

	@Autowired ManageDatesMapper manageDatesMapper;
	
	
	public List<ManageDates> findAll() {
		return manageDatesMapper.findAll();
	}
	
	//全体検索結果に計算を行う	
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

	
	
	public ManageDates findOne(String id) {
		try {
			return manageDatesMapper.findOne(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ManageDatesNotFoundException("指定された値が存在しません");
		}
	}
	

		public void insert(ManageDates manageDates) {
			manageDatesMapper.insert(manageDates);
			
		}
		
	
		public boolean update(ManageDates manageDates) {
//			一件更新
			int num = manageDatesMapper.update(manageDates);
			
			boolean result = false;
			
			if(num > 0) {
				result = true;
			} 
				return result;
			}
		

		
		public boolean delete(String id) {
			int num = manageDatesMapper.delete(id);
			
			boolean result = false;
			
			if(num > 0) {
				result = true;
			} 
				return result;
			}
	
		}
		

