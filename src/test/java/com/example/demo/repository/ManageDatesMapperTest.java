package com.example.demo.repository;







import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.demo.domain.ManageDates;




@SpringJUnitConfig //Junit5上でSpring TestContext Frameworkを利用することを示す
@SpringBootTest //毎回サーバ起動
@ActiveProfiles("test")//application.ymlのtestを対応（DBの設定を読み込む）
@DisplayName("ManageDatesMapperの結合テスト")
public class ManageDatesMapperTest {
//	実際にデータベースを動かすのでスタブは
	@Autowired
	private ManageDatesMapper sut;
	
	
	@Test
	void 全件検索して結果をリストで取得できること() throws Exception {
		List<ManageDates> actual = sut.findAll();
		assertEquals(1, actual.size());
	}
	
	@Test
	void 検索_1件して結果がキーに紐付く１件取得できること() throws Exception{
			ManageDates actual = sut.findOne("Y01");
			assertEquals(actual.getId(),"Y01");
			assertEquals(actual.getName(),"今週");
			assertEquals(actual.getYear(), 0);
			assertEquals(actual.getMonth(), 0);
			assertEquals(actual.getDate(), 3);
	
	}
	
	@Test
	public void 存在しないデータを検索すると結果がNULLとなること() throws Exception {
		ManageDates actual = sut.findOne("EmptyData");
		// TODO:サービス側で修正し、Nullを返さないようにするべき。
		assertEquals(actual, (null));
	}
		
	@Test
	public void nullで検索するとnullが帰ってくること() throws Exception {
		ManageDates actual = sut.findOne(null);
		// TODO:サービス側で修正し、Nullを返さないようにするべき。
		assertEquals(null, actual);
	}
	
	@Test
	public void 新規データの登録ができること() throws Exception {
		ManageDates manageDates = new ManageDates();
		manageDates.setId("Test");
		manageDates.setName("テスト");
		manageDates.setYear(0);
		manageDates.setMonth(0);
		manageDates.setDate(0);
		
		sut.insert(manageDates);
		ManageDates actual = sut.findOne("Test");
		
		assertEquals("テスト", actual.getName());
		
	}
	
	@Test
	public 

	
	
}
