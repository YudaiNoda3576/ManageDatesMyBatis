package com.example.demo.repository;





import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;


import com.example.demo.domain.ManageDates;
import com.example.demo.repository.ManageDatesMapper;



@SpringJUnitConfig //Junit5上でSpring TestContext Frameworkを利用することを示す
@SpringBootTest //毎回サーバ起動
@ActiveProfiles("unit")//application-unit.ymlのunitを対応（DBの設定を読み込む）
@DisplayName("ManageDatesServiceの結合テスト")
public class ManageDatesMapperTest {
	
	@Autowired
	private ManageDatesMapper sut;
	
	
	@Test
	@DisplayName("全件検索結果がリストで取得できるかのテスト")
	void 全件検索して結果をリストで取得できること() throws Exception {
		List<ManageDates> actual = sut.findAll();
		assertEquals(actual.size(), 3);
	}
	
	@Test
	void 検索_1件して結果がキーに紐付く１件だけ取得できること() throws Exception{
			ManageDates actual = sut.findOne("Y01");
			assertEquals(actual.getId(),"Y01");
			assertEquals(actual.getName(),"今週");
			assertEquals(actual.getYear(), 1);
			assertEquals(actual.getMonth(), 1);
			assertEquals(actual.getDate(), 1);
	
	}
	
	
	@Test
	void 存在しないIDで検索すると例外が発生すること() throws Exception {
		assertThrows(DataAccessException.class, () -> sut.findOne("あ"));
	}
		

	
	
}
