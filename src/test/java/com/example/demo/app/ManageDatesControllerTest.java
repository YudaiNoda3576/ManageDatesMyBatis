package com.example.demo.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.service.ManageDatesService;



//MockMvcを使用するとnullpointerexceptionが発生するのでこのアノテーションで解消
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("ManageDatesContrllorerの単体テスト")
public class ManageDatesControllerTest {

//	依存しているサービスクラスをモック化
@MockBean
ManageDatesService service;

@InjectMocks
private ManageDatesController target;

//@Autowired 
//private ManageDatesController target;

@Autowired
private MockMvc sut;

//＠AutoConfigureMockMvcを使用したら不要だった。何故これが動かないかは謎。
//@Before
//public void setUp() throws Exception {
////	tymeleafを使用している場合、MockMVCはHTMLファイルテンプレートの場所やページファイルの拡張子が.htmlであることを理解してくれない。
////	そのため、循環ビューが発生しているなどのエラーが吐かれる。以下に明示して対処。
//	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//	viewResolver.setPrefix("classpath:templates/");
//	viewResolver.setSuffix(".html");
//
//	sut = MockMvcBuilders.standaloneSetup(target).setViewResolvers(viewResolver).build();
//}


@Test
public void 計算実行画面のリクエストが正常となりViewとしてindexが返ること() throws Exception {
	sut.perform(get("/index"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
}

@Test
public void 新規登録画面のリクエストが正常となりViewとしてcreateが返ること() throws Exception {
	sut.perform(get("/create"))
		.andExpect(status().isOk())
		.andExpect(view().name("/create"));
}

@Test
public void 編集画面のリクエストが正常となりViewとしてeditが返ること() throws Exception {
	sut.perform(get("/edit/{id}"))
	   .andExpect(status().isOk())
	   .andExpect(view().name("/edit"));
}

}

