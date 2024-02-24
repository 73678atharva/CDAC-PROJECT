package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.Exception.ProductException;
import com.app.model.Product;
import com.app.request.CreateProductRequest;
import com.app.response.ApiResponse;
import com.app.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class AdminProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/create")
	public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest req){
		System.out.println("in product controller");
		Product product = productService.createProduct(req);
		return new ResponseEntity<Product>(product,HttpStatus.CREATED);
	}
	
	@DeleteMapping("delete/{productId}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("productId") int productId) throws ProductException{
		System.out.println("delete");
		productService.deleteProduct(productId);
		ApiResponse res = new ApiResponse();
		res.setMessage("Product deleted successfully");
		res.setStatus(true);
		return new ResponseEntity<>(res,HttpStatus.OK);	
	}
	
	@GetMapping("all")
	public List<Product> findAllProduct(){
		System.out.println("in find controller");
     List<Product> productPage = productService.getAllProduct();
     System.out.println(productPage);
     return productPage;
	}
	
	@PutMapping("/update/{productId}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product req, @PathVariable("productId") int  productId)
	throws ProductException{
		Product product = productService.updateProduct( productId, req);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@PostMapping("/creates")
	public ResponseEntity<ApiResponse> createMultipleProudcts(@RequestBody CreateProductRequest[] req){
		
		for(CreateProductRequest product:req) {
			productService.createProduct(product);
		}
		ApiResponse res = new ApiResponse();
		res.setMessage("Product created successfully");
		res.setStatus(true);
		return new ResponseEntity<>(res,HttpStatus.CREATED);	
	}
	
}
