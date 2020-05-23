package product.repository;



import static org.hamcrest.CoreMatchers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.ManageDates;
import com.example.demo.repository.ManageDatesMapper;


@RunWith(SpringRunner.class)
@MybatisTest
@TestPropertySource(locations = "classpath:test.properties")
@DisplayName("ManageDatesMapperの単体テスト")
@Transactional
public class ManageDatesMapperTest {
	
	@Autowired
	ManageDatesMapper sut;
	
	

	
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
