package product.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.domain.ManageDates;
import com.example.demo.repository.ManageDatesMapper;
import com.example.demo.service.ManageDatesService;




@ExtendWith(MockitoExtension.class)
@DisplayName("ManageDatesServiceの単体テスト")
public class ManageDatesServiceTest {
//	ダミーオブジェクト
	@Mock
	private ManageDatesMapper manageDatesMapper; 
//	テスト対象のクラス
	@InjectMocks 
	private ManageDatesService manageDatesService;
	
	
	@Test
	@DisplayName("全件取得で0件の場合のテスト")
	void 全件取得で0件の場合0件で結果が返ってくること() {
//		空のリスト
		List<ManageDates> list = new ArrayList<>();
//		モッククラスのI/Oをセット
		when(manageDatesMapper.findAll()).thenReturn(list);
//		サービスの実行
		List<ManageDates> actualList = manageDatesService.findAll();
//		実行回数の検査
		verify(manageDatesMapper, times(1)).findAll();
//		戻り値の検査
		assertEquals(0, actualList.size());
	}
	
	@Test
	@DisplayName("全件取得で２件の場合のテスト")
	
	void testFindAllReturnKist() {
		
//		モックから返すListに2つのTaskオブジェクトをセット
		List<ManageDates> list = new ArrayList<>();
		ManageDates md1 = new ManageDates();
		ManageDates md2 = new ManageDates();
		list.add(md1);
		list.add(md2);
//		モッククラスのI/O
		when(manageDatesMapper.findAll()).thenReturn(list);
//		サービスを実行
		List<ManageDates> actualList = manageDatesService.findAll();
//		モックの指定メソッドの実行回数を検査
		verify(manageDatesMapper, times(1)).findAll();
//		戻り値の検査
		assertEquals(2, actualList.size());
	}
	
//	@Test
////	このコードだとDBの接続ありきの話になってる
////	実際にやるのは、serviceからidを指定した時に値を引っ張ってこれるかどうかのテスト
////	DB接続ありきではなく、ここで値を設定する
//	@DisplayName("一件取得した場合のテスト")
//		void testGetIdReturnOne() {		
////	インスタンス化
//		ManageDates manageDates = new ManageDates();
//		String id = "Y01";
//		when(manageDatesMapper.findOne(id)).thenReturn(manageDates);
////		サービスの実行
//		ManageDates actualManageDates = manageDatesService.findOne(id);
//		
//		verify(manageDatesMapper, times(1)).findOne(id);
//		
//		assertEquals(id, actualManageDates.getId());
//	}
	
	@Test
	@DisplayName("一件取得した場合のテスト")
	void testGetIdReturnOne() {		
//インスタンス化
	ManageDates manageDates = new ManageDates();
	final String TABLE = "manage_dates_test";
	final String SELECT_SQL = String.format("SELECT * FROM manage_dates_test WHERE ID = ?" , TABLE);
	String id = "Y01";
	when(manageDatesMapper.findOne(id)).thenReturn(manageDates);
}
	
//	@Test
//	@DisplayName("削除対象が存在しない場合、例外が発生することを確認するテスト")
//	void throwNotFoundException(){
//	
//	}
	
}
