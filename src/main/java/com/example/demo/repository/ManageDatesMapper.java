package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.ManageDates;


@Mapper
public interface ManageDatesMapper {
	public List<ManageDates> findAll();
	
	public ManageDates findOne(String id);
	
	public void insert(ManageDates manageDates);
	
	public int update(ManageDates manageDates);
	
	public int delete(String id);
}
