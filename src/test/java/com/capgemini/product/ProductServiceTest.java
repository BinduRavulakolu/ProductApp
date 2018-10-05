package com.capgemini.product;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.product.entities.Product;
import com.capgemini.product.repository.ProductRepository;
import com.capgemini.product.service.exceptions.ProductNotFoundException;
import com.capgemini.product.service.impl.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@Mock
	ProductRepository productRepository;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productServiceImpl).build();
	}

	@Test
	public void testAddProduct() {
		Product product = new Product(12345, "Lenovo", "computer", 120.0);
		when(productRepository.save(product)).thenReturn(product);
		assertEquals(productServiceImpl.addProduct(product), product);
	}

	@Test
	public void testFindProductById() throws ProductNotFoundException {
		Product product = new Product(12345, "Lenovo", "computer", 120.0);
		Optional<Product> product1 = Optional.of(product);
		when(productRepository.findById(12345)).thenReturn(product1);
		assertEquals(productServiceImpl.findProductById(12345), product);
	}

	@Test
	public void testUpdateProduct() throws ProductNotFoundException {
		Product product = new Product(12345, "dell", "computer", 120.0);
		Product product2 = new Product(12345, "Lenovo", "computer", 120.0);
		when(productRepository.save(product)).thenReturn(product2);
		assertEquals(productServiceImpl.updateProduct(product), product2);
	}

	@Test
	public void testDeleteProduct() {
//		Product product = new Product(12345, "dell", "computer", 120.0);
		productServiceImpl.deleteProduct(12345);
		verify(productRepository, times(1)).deleteById(12345);
	}

}
