package com.capgemini.product.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.product.entities.Product;
import com.capgemini.product.repository.ProductRepository;
import com.capgemini.product.service.ProductService;
import com.capgemini.product.service.exceptions.ProductNotFoundException;


@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(int productId) throws ProductNotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(optionalProduct.isPresent())
			return optionalProduct.get();
		System.out.println(optionalProduct.get());
		throw new ProductNotFoundException("Product does not exists");
	}
	
	@Override
	public Product updateProduct(Product product) {
		
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(int productId) {
		productRepository.deleteById(productId);
	}

}
