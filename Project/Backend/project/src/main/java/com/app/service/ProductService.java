package com.app.service;

import java.util.List;


import com.app.Exception.ProductException;
import com.app.model.Product;
import com.app.request.CreateProductRequest;

public interface ProductService {
	
	public Product createProduct(CreateProductRequest req);
	
	public String deleteProduct(int productId) throws ProductException;
	
	public Product updateProduct(int productId, Product req)throws ProductException;
	
	public Product findProductById(int productId) throws ProductException;
	
	public List<Product> findProductByCatgeory (String Catgeory);
	
	public List<Product> getAllProduct();
//
//	public Page<Product> getAllProduct(Integer minPrice, Integer maxPrice, Integer minDiscount, Integer maxDiscount,
//			Pageable pageable, String stock, Integer pageNumber, Integer pageSize);	
}
