package com.example.demo.service;

import java.time.LocalDate;

import java.util.List;

import com.example.demo.domain.ManageDates;


public interface ManageDatesService {
	
	List<ManageDates> findAll();
	
	List<LocalDate> search(String input);
	
	ManageDates findOne(String id);
    
	void insert(ManageDates manageDates);
	
	boolean update(ManageDates manageDates);
	
	boolean delete(String id);
	
}
