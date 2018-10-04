package com.capgemini.product;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.product.controller.ProductController;
import com.capgemini.product.entities.Product;
import com.capgemini.product.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {

	@InjectMocks
	private ProductController productController;

	@Mock
	private ProductService productService;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	public void testAddProductJsonObject() throws Exception {

		when(productService.addProduct(Mockito.isA(Product.class)))
				.thenReturn(new Product(1234, "xxxx", "yyyy", 123.0));
		
		String content = "{\"productId\": 1234, \"productName\": \"xxxx\",\"productCategory\": \"yyyy\",  \"productPrice\": 123.0}";
		
		mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(content)

				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").exists()).andExpect(jsonPath("$.productName").exists())
				.andExpect(jsonPath("$.productCategory").exists()).andExpect(jsonPath("$.productPrice").exists())
				.andExpect(jsonPath("$.productName").value("xxxx"))
				.andExpect(jsonPath("$.productCategory").value("yyyy")).andDo(print());
	}
}
